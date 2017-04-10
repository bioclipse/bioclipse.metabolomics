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
package net.bioclipse.mom.business;

import java.util.List;
import java.util.Set;

import org.bridgedb.Xref;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="Manager to support metabolite mapping using ontologies."
)
public interface IMomManager extends IBioclipseManager {

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="String identifier"
    )
    public List<String> mapAll(String identifier) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="Xref source"
    )
    public Set<Xref> mapAll(Xref source) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="String identifier"
    )
    public List<String> mapSuperClasses(String identifier) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="Xref source"
    )
    public Set<Xref> mapSuperClasses(Xref source) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="String identifier"
    )
    public List<String> mapChargeStates(String identifier) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="Xref source"
    )
    public Set<Xref> mapChargeStates(Xref source) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="String identifier"
    )
    public List<String> mapRoles(String identifier) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="Xref source"
    )
    public Set<Xref> mapRoles(Xref source) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="String identifier"
    )
    public List<String> mapTautomers(String identifier) throws BioclipseException;

    @Recorded
    @PublishedMethod(
        methodSummary="Uses an ontology approach to map something identified in the ChEBI database " +
        		"to any other known database. It returns a Set of Xref objects.",
        params="Xref source"
    )
    public Set<Xref> mapTautomers(Xref source) throws BioclipseException;


}
