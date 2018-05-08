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

import org.apache.log4j.Logger;
import org.bridgedb.BridgeDb;
import org.bridgedb.IDMapper;
import org.bridgedb.Xref;
import org.bridgedb.bio.DataSourceTxt;

import net.bioclipse.bridgedb.business.BridgedbManager;
import net.bioclipse.core.business.BioclipseException;
import net.bioclipse.managers.business.IBioclipseManager;

public class MomManager implements IBioclipseManager {
	
    private static final Logger logger = Logger.getLogger(MomManager.class);
    
    private static IDMapper allMapper;
    private static IDMapper superMapper;
    private static IDMapper chargeMapper;
    private static IDMapper rolesMapper;
    private static IDMapper tautomerMapper;
    private static IDMapper enantiomerMapper;
    private static BridgedbManager bridgedb = new BridgedbManager();
    
    static {
    	try {
			Class.forName("org.bridgedb.mapper.chebi.ChEBIIDMapper");
			DataSourceTxt.init();
			MomManager.allMapper = BridgeDb.connect("idmapper-chebi:matchSuperClass,matchChargeStates,matchRoles,matchTautomers,matchEnantiomers");
			MomManager.superMapper = BridgeDb.connect("idmapper-chebi:matchSuperClass");
			MomManager.chargeMapper = BridgeDb.connect("idmapper-chebi:matchChargeStates");
			MomManager.rolesMapper = BridgeDb.connect("idmapper-chebi:matchRoles");
			MomManager.tautomerMapper = BridgeDb.connect("idmapper-chebi:matchTautomers");
			MomManager.enantiomerMapper = BridgeDb.connect("idmapper-chebi:matchEnantiomers");
		} catch (Exception e) {
			logger.error("Could not set up the ontology mapper: " + e.getMessage());
		}
    }

    /**
     * Gives a short one word name of the manager used as variable name when
     * scripting.
     */
    public String getManagerName() {
        return "mom";
    }

    public List<String> mapAll(String identifier) throws BioclipseException {
    	return bridgedb.map(allMapper, identifier, "Ce");
    }

    public Set<Xref> mapAll(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(allMapper, source);
    }

    public List<String> mapSuperClasses(String identifier) throws BioclipseException {
    	return bridgedb.map(superMapper, identifier, "Ce");
    }

    public Set<Xref> mapSuperClasses(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(superMapper, source);
    }

    public List<String> mapChargeStates(String identifier) throws BioclipseException {
    	return bridgedb.map(chargeMapper, identifier, "Ce");
    }

    public Set<Xref> mapChargeStates(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(chargeMapper, source);
    }

    public List<String> mapRoles(String identifier) throws BioclipseException {
    	return bridgedb.map(rolesMapper, identifier, "Ce");
    }

    public Set<Xref> mapRoles(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(rolesMapper, source);
    }

    public List<String> mapTautomers(String identifier) throws BioclipseException {
    	return bridgedb.map(tautomerMapper, identifier, "Ce");
    }

    public Set<Xref> mapTautomers(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(tautomerMapper, source);
    }

    public List<String> mapEnantiomers(String identifier) throws BioclipseException {
    	return bridgedb.map(enantiomerMapper, identifier, "Ce");
    }

    public Set<Xref> mapEnantiomers(Xref source) throws BioclipseException {
    	if (!source.getDataSource().getSystemCode().equals("Ce"))
    		throw new BioclipseException("Xref must be from ChEBI");
    	return bridgedb.map(enantiomerMapper, source);
    }
}
