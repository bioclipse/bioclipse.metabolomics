/*******************************************************************************
 * Copyright (c) 2010  Egon Willighagen <egonw@users.sf.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.metfrag.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.core.domain.ISpectrum;
import net.bioclipse.managers.business.IBioclipseManager;
import net.bioclipse.spectrum.business.SpectrumManager;
import net.bioclipse.spectrum.domain.JumboSpectrum;

import org.apache.log4j.Logger;
import org.openscience.cdk.AtomContainerSet;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.xmlcml.cml.element.CMLSpectrum;
import org.xmlcml.cml.element.CMLPeak;

import de.ipbhalle.metfrag.massbankParser.Spectrum;
import de.ipbhalle.metfrag.massbankParser.Peak;

import net.bioclipse.cdk.domain.ICDKMolecule;

public class MetfragManager implements IBioclipseManager {

    private static final Logger logger = Logger.getLogger(MetfragManager.class);
    private Double mzabs = 0.001;
    private Double mzppm = 10.0;
    private Integer depth = 2;
        
    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "metfrag";
    }

    public void setAccuracy(Double mzabs, Double mzppm) 
    {
    	this.mzabs = mzabs;
    	this.mzppm = mzppm;
    }

    public void setDepth(Integer depth) 
    {
    	this.depth = depth;
    }

    public Double score(ISpectrum spectrum, IMolecule molecule) throws BioclipseException
    {   	
        List<IMolecule> molecules = new ArrayList();
        molecules.add(molecule);

    	List<Double> result = scores(spectrum, molecules); 
		return result.get(0);
    }
    
    public List<Double> scores(ISpectrum spectrum, List<IMolecule> molecules) throws BioclipseException {
		List<Double> result = new ArrayList<Double>();

		String peaks = "119.051 467.616 45\n123.044 370.662 36\n" +
		"147.044 6078.145 606\n153.019 10000.0 999\n179.036 141.192 13\n189.058 176.358 16\n" +
		"273.076 10000.000 999\n274.083 318.003 30\n";

		SpectrumManager sm = new SpectrumManager(); 
		JumboSpectrum jspectrum = (JumboSpectrum) sm.create(spectrum);
		CMLSpectrum cspectrum = jspectrum.getJumboObject();

		Vector<Peak> mfpeaks = new Vector<Peak>();
		logger.info("Adding peaks ... ");
		for (CMLPeak peak : cspectrum.getPeakListElements().getList().get(0).getPeakElements()) {
			logger.info("Adding " + peak.getXValue() + " " +  peak.getYValue());
			mfpeaks.add(new Peak(peak.getXValue(), peak.getYValue(), -1)); 
		}
		logger.info("Adding peaks done");
				
		Spectrum mfspectrum = new Spectrum(-1 /*CE eV */ , 
											mfpeaks, 
											272.06847 /* mass */ , 1 /*mode*/,  
											"" /* inchi */ , 0 /*CID*/ , 
											"" /* KEGG */ , "" /* Name */, "C15H12O5" /*formula*/  );
		

		try {
			BioClipseConvenience bcc = new BioClipseConvenience(mzabs, mzppm, mfspectrum, molecules, true, "", 1, 272.06847);
			logger.info("And the score is...");
			List<Double> mfresult = bcc.metFrag();
			logger.info("We got " + mfresult.size() + "results.");			
			result.addAll(mfresult);		
		} catch (Exception e) {
			e.printStackTrace();
			result.add(-1.0);
		}
		return result;
		
    }
    
//
//    
//	public List<Double> calculateMetFragScore()
//			throws BioclipseException {
//		
//		List<Double> result = new ArrayList<Double>();
//		// Do calculation
//
//		// For now lets create the ISpectrum spectrum,
//		// Later move ISpectrum spectrum into method parameters
//		IJumboSpectrum jtmpspectrum = new JumboSpectrum();
//        jtmpspectrum.getJumboObject().addPeakList(new CMLPeakList());
//		ISpectrum spectrum = jtmpspectrum;
//
//		// ... and extract peaks:
//		
//		String peaks = "119.051 467.616 45\n123.044 370.662 36\n" +
//		"147.044 6078.145 606\n153.019 10000.0 999\n179.036 141.192 13\n189.058 176.358 16\n" +
//		"273.076 10000.000 999\n274.083 318.003 30\n";
//		
//		String smiles = "C1C(OC2=CC(=CC(=C2C1=O)O)O)C3=CC=C(C=C3)O";
//		IAtomContainer molecule = DefaultChemObjectBuilder.getInstance().newAtomContainer();
//
//		//parse smiles		
//		try {
//			SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
//			molecule = sp.parseSmiles(smiles);
//			//configure atoms
//			AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(molecule);
//			//add all hydrogens explicitly
//			CDKHydrogenAdder adder1 = CDKHydrogenAdder.getInstance(molecule.getBuilder());
//			adder1.addImplicitHydrogens(molecule);
//			AtomContainerManipulator.convertImplicitToExplicitHydrogens(molecule);
//		} catch (InvalidSmilesException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (CDKException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//
//        // Fake a list of molecules
//        IAtomContainerSet molecules = new AtomContainerSet();
//        molecules.addAtomContainer(molecule);
//        		
//		try {
//			BioClipseConvenience bcc = new BioClipseConvenience(0.01, 50.0, peaks, molecules, true, "", 1, 272.06847);
//			logger.info("And the score is...");
//			result = bcc.metFrag();		
//		} catch (Exception e) {
//			System.out.println("Error! TODO...");
//			e.printStackTrace();
//		}
//		return result;		
//     }
}
