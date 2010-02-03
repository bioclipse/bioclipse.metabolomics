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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.jobs.IReturner;
import net.bioclipse.managers.business.IBioclipseManager;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.openscience.cdk.ChemFile;
import org.openscience.cdk.ChemObject;
import org.openscience.cdk.atomtype.CDKAtomTypeMatcher;
import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomType;
import org.openscience.cdk.io.MDLReader;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;
import org.openscience.cdk.tools.manipulator.AtomTypeManipulator;
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;

import de.ipbhalle.metfrag.chemspiderClient.ChemSpider;
import de.ipbhalle.metfrag.fragmenter.Fragmenter;
import de.ipbhalle.metfrag.keggWebservice.KeggWebservice;
import de.ipbhalle.metfrag.main.AssignFragmentPeak;
import de.ipbhalle.metfrag.main.CleanUpPeakList;
import de.ipbhalle.metfrag.main.MetFlowConvenience;
import de.ipbhalle.metfrag.main.PeakMolPair;
import de.ipbhalle.metfrag.main.WrapperSpectrum;
import de.ipbhalle.metfrag.massbankParser.Peak;
import de.ipbhalle.metfrag.pubchem.PubChemWebService;
import de.ipbhalle.metfrag.scoring.Scoring;
import de.ipbhalle.metfrag.tools.MolecularFormulaTools;
import de.ipbhalle.metfrag.tools.PPMTool;

public class MetfragManager implements IBioclipseManager {

    private static final Logger logger = Logger.getLogger(MetfragManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "metfrag";
    }

	public void calculateMetFragScore()
			throws BioclipseException {
		List<Float> result = new ArrayList<Float>();
		// Do calculation
		
//      MetFlowConvenience test = new MetFlowConvenience();
//      try {
//              System.out.println(test.metFrag());
//      } catch (Exception e) {
//              e.printStackTrace();
//      }
        String peaks = "119.051 467.616 45\n123.044 370.662 36\n" +
        "147.044 6078.145 606\n153.019 10000.0 999\n179.036 141.192 13\n189.058 176.358 16\n" +
        "273.076 10000.000 999\n274.083 318.003 30\n";
        MetFlowConvenience mfc = new MetFlowConvenience(0.01, 50.0, 10.0, peaks, "kegg", true, "", 50, 1, 272.06847);
       
        try {
                System.out.println(mfc.metFrag());
        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
	}
}
