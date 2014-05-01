package de.blauePandas.LMSServer.core;

import java.sql.Connection;

/**
 * @author havoc
 * 
 * represents a single connection from a pool of connections.
 * doesn't really do anything on its own
 */
public class DBConnection {
    Connection Data = null;
    boolean inUse = false;

    /**
     * conscructor, to be called only from ConnectionPool
     * 
     * @param _data the actual database-connection
     */
    protected DBConnection(Connection _data) {
        this.Data = _data;
        this.inUse = false;
    } // constructor 1

    /**
     * constructor, to be called only from ConnectionPool
     * 
     * @param _data the actual database-connection
     * @param _inUse boolean value of the in-use marker
     */
    protected DBConnection(Connection _data, boolean _inUse) {
        this.Data   = _data;
        this.inUse  = _inUse;
        
    } // constructor 2
    
} // DBConnection
