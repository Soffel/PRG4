package de.blauePandas.LMSServer.core;

import java.sql.Connection;

/**
 * @author havoc
 * 
 * represents a single connection from a pool of connections
 */
public class DBConnection implements ConnectionInterface {
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

    // todo
    @Override public void prepare(String _Stmt) {
    } // prepare
    
    // todo
    @Override public void bind(int i, String _param) {
    } // bind
    
    // todo
    @Override public String execute() {
        String result = null;
        
        return result;
    } // execute
    
} // DBConnection
