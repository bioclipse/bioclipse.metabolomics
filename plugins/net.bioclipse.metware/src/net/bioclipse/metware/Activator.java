/*******************************************************************************
 * Copyright (c) 2009  Egon Willighagen <egonw@users.sf.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: Bioclipse Project <http://www.bioclipse.net>
 ******************************************************************************/
package net.bioclipse.metware;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

    private static Activator myself;
    public final static String PLUGIN_ID="net.bioclipse.metware";

    public Activator() {
        myself = this;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        myself = this;
    }

    public void stop(BundleContext context) throws Exception {
        myself = null;
        super.stop(context);
    }

    public static Activator getDefault() {
        return myself;
    }

}
