/*******************************************************************************
 * Copyright (c) 2009  Egon Willighagen <egonw@user.sf.net>
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.metware.preferences;

import net.bioclipse.metware.Activator;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for the MetWare functionality.
 */
public class MetWarePreferencePage extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

    public static final String METWARE_DATABASE = "database";
    public static final String METWARE_USER_ACCOUNT = "user";
    public static final String METWARE_USER_PASSWORD = "password";

    private StringFieldEditor database;
    private StringFieldEditor user;
    private StringFieldEditor password;

    public MetWarePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("MetWare Preferences");
	}
    
	public void createFieldEditors() {
        database = new StringFieldEditor(
            METWARE_DATABASE,
            "&Database",
            getFieldEditorParent()
        );
        addField(database);

        user = new StringFieldEditor(
            METWARE_USER_ACCOUNT,
            "&User",
            getFieldEditorParent()
        );
        addField(user);

        password = new StringFieldEditor(
            METWARE_USER_PASSWORD,
            "&Password",
            getFieldEditorParent()
        );
        addField(password);
	}

	public void init(IWorkbench workbench) {}

}