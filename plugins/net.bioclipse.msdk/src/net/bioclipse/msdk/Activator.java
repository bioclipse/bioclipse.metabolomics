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
package net.bioclipse.msdk;

import net.bioclipse.msdk.business.IMsdkManager;
import net.bioclipse.msdk.business.IJavaMsdkManager;
import net.bioclipse.msdk.business.IJavaScriptMsdkManager;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Activator class controls the plug-in life cycle
 */
public class Activator {

    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

    // The shared instance
    private static Activator plugin;

    // Trackers for getting the managers
    private ServiceTracker javaFinderTracker;
    private ServiceTracker jsFinderTracker;

    public Activator() {
    }

    public void start(BundleContext context){
        plugin = this;
        javaFinderTracker
            = new ServiceTracker( context,
                                  IJavaMsdkManager.class.getName(),
                                  null );

        javaFinderTracker.open();
        jsFinderTracker
            = new ServiceTracker( context,
                                  IJavaScriptMsdkManager.class.getName(),
                                  null );

        jsFinderTracker.open();
    }

    public void stop(BundleContext context) {
        plugin = null;
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    public IMsdkManager getJavaMsdkManager() {
        IMsdkManager manager = null;
        try {
            manager = (IMsdkManager)
                      javaFinderTracker.waitForService(1000*10);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(
                          "Could not get the Java MsdkManager",
                          e );
        }
        if (manager == null) {
            throw new IllegalStateException(
                          "Could not get the Java MsdkManager");
        }
        return manager;
    }

    public IJavaScriptMsdkManager getJavaScriptMsdkManager() {
        IJavaScriptMsdkManager manager = null;
        try {
            manager = (IJavaScriptMsdkManager)
                      jsFinderTracker.waitForService(1000*10);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(
                          "Could not get the JavaScript MsdkManager",
                          e );
        }
        if (manager == null) {
            throw new IllegalStateException(
                          "Could not get the JavaScript MsdkManager");
        }
        return manager;
    }
}
