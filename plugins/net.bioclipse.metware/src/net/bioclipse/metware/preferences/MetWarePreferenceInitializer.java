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
package net.bioclipse.metware.preferences;

import net.bioclipse.metware.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class MetWarePreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        
        store.setDefault(
            MetWarePreferencePage.METWARE_DATABASE,
            "localhost"
        );
        store.setDefault(
            MetWarePreferencePage.METWARE_USER_ACCOUNT,
            ""
        );
        store.setDefault(
            MetWarePreferencePage.METWARE_USER_PASSWORD,
            ""
        );
    }

}
