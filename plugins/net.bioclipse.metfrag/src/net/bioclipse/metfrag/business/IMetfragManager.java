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

import org.eclipse.core.runtime.IProgressMonitor;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.jobs.IReturner;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="The MetFrag manager is the interface to the in-silico Fragmentation tool."
)
public interface IMetfragManager extends IBioclipseManager {
	@PublishedMethod(params = "", 
	                 methodSummary = "Runs a pre-canned query against KEGG :-)")
	public List<Double> calculateMetFragScore() throws BioclipseException;
}
