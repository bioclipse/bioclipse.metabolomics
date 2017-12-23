/*******************************************************************************
 * Copyright (c) 2017  Egon Willighagen <egon.willighagen@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.ambit.smirks.business;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.aromaticity.ElectronDonation;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.silent.AtomContainerSet;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import ambit2.core.data.MoleculeTools;
import ambit2.smarts.SMIRKSManager;
import ambit2.smarts.SMIRKSReaction;
import ambit2.smarts.SmartsConst;
import net.bioclipse.cdk.business.Activator;
import net.bioclipse.cdk.business.ICDKManager;
import net.bioclipse.cdk.domain.ICDKMolecule;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.managers.business.IBioclipseManager;

public class SmirksManager implements IBioclipseManager {

	private final ICDKManager   cdk    = Activator.getDefault().getJavaCDKManager();
	private final SMIRKSManager smrkMan = new SMIRKSManager(
		SilentChemObjectBuilder.getInstance()
	);
			
    private static final Logger logger = Logger.getLogger(SmirksManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "smirks";
    }

    public List<ICDKMolecule> transform(IMolecule molecule, String smirks, IProgressMonitor monitor)
            throws BioclipseException {
    	if (monitor == null) monitor = new NullProgressMonitor();
    	smrkMan.setFlagSSMode(SmartsConst.SSM_MODE.SSM_NON_IDENTICAL_FIRST);
		smrkMan.setFlagProcessResultStructures(true);
		smrkMan.setFlagClearHybridizationBeforeResultProcess(true);
		smrkMan.setFlagClearImplicitHAtomsBeforeResultProcess(true);
		smrkMan.setFlagClearAromaticityBeforeResultProcess(true);
		smrkMan.setFlagAddImplicitHAtomsOnResultProcess(true);
		smrkMan.setFlagConvertAddedImplicitHToExplicitOnResultProcess(false);
		smrkMan.setFlagConvertExplicitHToImplicitOnResultProcess(true);
		smrkMan.getSmartsParser().mSupportDoubleBondAromaticityNotSpecified = false;
		smrkMan.setFlagApplyStereoTransformation(true);
    	
    	logger.info("Converting a molecule with the following SMIRKS: " + smirks);
    	List<ICDKMolecule> mols = Collections.emptyList();
    	try {
    		mols = cdk.createMoleculeList();
    	} catch (Exception exception) {
			throw new BioclipseException("Error while creating a product list: " + exception.getMessage(), exception);
		}
    	String smiles = cdk.calculateSMILES(molecule);
    	logger.debug("Converted molecule to SMILES: " + smiles);
    	SMIRKSReaction reaction = smrkMan.parse(smirks);
        if (!smrkMan.getErrors().equals(""))  {
             throw new BioclipseException("SMIRKS Parser errors: " + smrkMan.getErrors());
        }
        try {
        	SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
        	IAtomContainer cdkMol = parser.parseSmiles(smiles);
        	for (IAtom atom : cdkMol.atoms())
				if (atom.getFlag(CDKConstants.ISAROMATIC))
					atom.setFlag(CDKConstants.ISAROMATIC, false);
			for (IBond bond : cdkMol.bonds())
				if (bond.getFlag(CDKConstants.ISAROMATIC))
					bond.setFlag(CDKConstants.ISAROMATIC, false);
			MoleculeTools.convertImplicitToExplicitHydrogens(cdkMol);
			Aromaticity aromaticity = new Aromaticity(ElectronDonation.daylight(),
					Cycles.or(Cycles.all(), Cycles.edgeShort()));
			aromaticity.apply(cdkMol);
        	logger.debug("Converted to an AmbitCDK IAtomContainer: " + cdkMol);

        	IAtomContainerSet rproducts = new AtomContainerSet();
//        	smrkMan.getAllMappings(cdkMol);
//        	rproducts.addAtomContainer(cdkMol);
        	rproducts = smrkMan.applyTransformationWithSingleCopyForEachPos(
        		cdkMol, null, reaction, SmartsConst.SSM_MODE.SSM_NON_IDENTICAL
        	);
        	if (rproducts != null) {
        		for (int i = 0; i < rproducts.getAtomContainerCount(); i++) {
        			IAtomContainer mol = rproducts.getAtomContainer(i);
        			AtomContainerManipulator.suppressHydrogens(mol);
					String newSmiles = SmilesGenerator.absolute().create(mol);
        			System.out.println("New SMILES: " + newSmiles);
        			mols.add(cdk.fromSMILES(newSmiles));
        		}
        	} else {
        		logger.warn("No products generated...");
        	}
        } catch (Exception exception) {
        	exception.printStackTrace();
        	throw new BioclipseException("Error while transforming the molecule: " + exception.getMessage(), exception);
        }
    	
    	return mols;
    }


}
