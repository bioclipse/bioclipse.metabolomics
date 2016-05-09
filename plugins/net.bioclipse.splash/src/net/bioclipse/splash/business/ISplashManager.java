/*******************************************************************************
 * Copyright (c) 2016  Egon Willighagen <egon.willighagen@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.splash.business;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="Wrapper for the SPLASH library (https://github.com/berlinguyinca/spectra-hash)."
)
public interface ISplashManager extends IBioclipseManager {

	@Recorded
    @PublishedMethod(
    	params="String spectrum",
        methodSummary = "Creates a SPLASH from a mass spectrum (e.g. '10:123.12 12:123.11 13:22 14:212')"
    )
	public String it(String spectrum);
	
}
