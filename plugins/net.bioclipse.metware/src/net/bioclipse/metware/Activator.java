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

import net.bioclipse.core.util.LogUtils;
import net.bioclipse.metware.business.IJavaMetwareManager;
import net.bioclipse.metware.business.IJavaScriptMetwareManager;
import net.bioclipse.metware.business.IMetwareManager;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends AbstractUIPlugin {

    private static Activator myself;
    public final static String PLUGIN_ID="net.bioclipse.metware";
    private static final Logger logger = Logger.getLogger(Activator.class);

    private ServiceTracker javaManagerTracker;
    private ServiceTracker javaScriptManagerTracker;

    public Activator() {
        myself = this;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        myself = this;
        javaManagerTracker = new ServiceTracker(
            context,
            IJavaMetwareManager.class.getName(), 
            null
        );
        javaManagerTracker.open();
        javaScriptManagerTracker = new ServiceTracker(
            context, 
            IJavaScriptMetwareManager.class.getName(), 
            null
        );
        javaScriptManagerTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
        myself = null;
        super.stop(context);
    }

    public static Activator getDefault() {
        return myself;
    }

    public IMetwareManager getJavaManager() {
        IMetwareManager manager = null;
        try {
            manager = (IMetwareManager) 
                      javaManagerTracker.waitForService(1000*10);
        } catch (InterruptedException e) {
            LogUtils.debugTrace(logger, e);
        }
        if (manager == null) {
            throw new IllegalStateException("Could not get the Gist manager");
        }
        return manager;
    }

    public IMetwareManager getJavaScriptManager() {
        IMetwareManager manager = null;
        try {
            manager = (IMetwareManager) 
                      javaScriptManagerTracker.waitForService(1000*10);
        } catch (InterruptedException e) {
            LogUtils.debugTrace(logger, e);
        }
        if (manager == null) {
            throw new IllegalStateException("Could not get the Gist manager");
        }
        return manager;
    }
}
