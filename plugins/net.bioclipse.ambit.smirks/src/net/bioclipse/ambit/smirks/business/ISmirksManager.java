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

import java.util.List;

import net.bioclipse.cdk.domain.ICDKMolecule;
import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.core.domain.IMolecule;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="Ambit-SMIRKS plugin.",
    doi={"10.1002/minf.201100028"}
)
public interface ISmirksManager extends IBioclipseManager {

    @Recorded
    @PublishedMethod( 
        params = "IMolecule molecule, String smirks",
        methodSummary = "Converts a molecule according to the given SMIRKS")
    public List<ICDKMolecule> transform(IMolecule molecule, String smirks)
                              throws BioclipseException;

}
