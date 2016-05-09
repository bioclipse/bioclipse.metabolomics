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

import net.bioclipse.managers.business.IBioclipseManager;

import org.apache.log4j.Logger;

import edu.ucdavis.fiehnlab.spectra.hash.core.types.SpectraType;
import edu.ucdavis.fiehnlab.spectra.hash.core.util.SplashUtil;

public class SplashManager implements IBioclipseManager {

    private static final Logger logger = Logger.getLogger(SplashManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "splash";
    }

    public String it(String spectrum) {
    	logger.debug("Splashing: " + spectrum);
    	String splash = SplashUtil.splash(spectrum, SpectraType.MS);
    	logger.debug(" got: " + splash);
    	return splash;
    }
}
