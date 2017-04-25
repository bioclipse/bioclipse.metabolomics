/*******************************************************************************
 * Copyright (c) 2015  Egon Willighagen <egonw@users.sf.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.msdk.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bioclipse.managers.business.IBioclipseManager;


public class MsdkManager implements IBioclipseManager {

    private static final Logger logger = LoggerFactory.getLogger(MsdkManager.class);

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "msdk";
    }
}
