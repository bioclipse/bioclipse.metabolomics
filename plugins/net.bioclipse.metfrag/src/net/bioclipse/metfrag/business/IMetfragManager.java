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

import java.util.List;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.core.domain.ISpectrum;
import net.bioclipse.jobs.IReturner;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="The MetFrag manager is the interface to the in-silico Fragmentation tool."
)
public interface IMetfragManager extends IBioclipseManager {

	@PublishedMethod(params = "Double mzabs, Double mzppm", 
            methodSummary = "Set the accuracy for matching fragment peaks. Defaults: 0.01 and 10ppm")
            public Double setAccuracy(Double mzabs, Double mzppm) throws BioclipseException;

	@PublishedMethod(params = "Integer depth", 
            methodSummary = "Set the tree depth for fragmentation. Default: 2")
            public Double setDepth(Integer depth) throws BioclipseException;

	@PublishedMethod(params = "ISpectrum spectrum, IMolecule molecule", 
            methodSummary = "Runs a canned spectrum against pre-canned molecule")
            public Double score(ISpectrum spectrum, IMolecule molecule) throws BioclipseException;
	
	@PublishedMethod(params = "ISpectrum spectrum, List<IMolecule> molecules", 
            methodSummary = "Runs a spectrum against several molecules")
            public List<Double> scores(ISpectrum spectrum, List<IMolecule> molecules) throws BioclipseException;

//	@PublishedMethod(params = "ISpectrum spectrum, IMolecule molecule", 
//            methodSummary = "Runs a canned spectrum against pre-canned molecule")
//            public IAtomContainerSet fragments(ISpectrum spectrum, IMolecule molecule) throws BioclipseException;

}
