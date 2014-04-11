package de.blauePandas.LMSServer.core;

import java.sql.Connection;

/**
 * @author havoc
 * 
 * represents a single connection from a pool of connections
 */
public class DBConnection {
    Connection Data = null;
    boolean inUse = false;

    protected DBConnection(Connection _data) {
        this.Data = _data;
        this.inUse = false;
    } // constructor 1

    protected DBConnection(Connection _data, boolean _inUse) {
        this.Data = _data;
        this.inUse      = _inUse;
        
    } // constructor 2
    
} // DBConnection
