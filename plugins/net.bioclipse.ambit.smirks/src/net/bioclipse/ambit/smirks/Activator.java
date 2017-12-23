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
package net.bioclipse.ambit.smirks;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import net.bioclipse.ambit.smirks.business.IJavaScriptSmirksManager;
import net.bioclipse.ambit.smirks.business.IJavaSmirksManager;
import net.bioclipse.ambit.smirks.business.ISmirksManager;

/**
 * The Activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    private static final Logger logger = Logger.getLogger(Activator.class);

    // The shared instance    // The plug-in ID
    public static final String PLUGIN_ID = "net.bioclipse.ambit.smirks";

    private static Activator plugin;

    // Trackers for getting the managers
    private ServiceTracker javaFinderTracker;
    private ServiceTracker jsFinderTracker;

    public Activator() {
    	logger.info("Activator constructed...");
    }

    public void start(BundleContext context) throws Exception {
    	logger.info("Starting the activator with context: "  + context);
        super.start(context);
        plugin = this;
        javaFinderTracker
            = new ServiceTracker( context,
                                  IJavaSmirksManager.class.getName(),
                                  null );

        javaFinderTracker.open();
        jsFinderTracker
            = new ServiceTracker( context,
                                  IJavaScriptSmirksManager.class.getName(),
                                  null );

        jsFinderTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
    	logger.info("Stopping the activator with context: "  + context);
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
    	logger.info("Getting the default activator: "  + plugin);
        return plugin;
    }

    public ISmirksManager getJavaSmirksManager() {
        ISmirksManager manager = null;
        try {
            manager = (ISmirksManager)
                      javaFinderTracker.waitForService(1000*10);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(
                          "Could not get the Java SmirksManager",
                          e );
        }
        if (manager == null) {
            throw new IllegalStateException(
                          "Could not get the Java SmirksManager");
        }
        return manager;
    }

    public IJavaScriptSmirksManager getJavaScriptSmirksManager() {
        IJavaScriptSmirksManager manager = null;
        try {
            manager = (IJavaScriptSmirksManager)
                      jsFinderTracker.waitForService(1000*10);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(
                          "Could not get the JavaScript SmirksManager",
                          e );
        }
        if (manager == null) {
            throw new IllegalStateException(
                          "Could not get the JavaScript SmirksManager");
        }
        return manager;
    }
}
