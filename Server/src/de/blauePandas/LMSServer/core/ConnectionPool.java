package de.blauePandas.LMSServer.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author havoc
 * 
 * singleton; manages database-connections, provides methods for access by DBMgr
 */

public class ConnectionPool {
    
    static ConnectionPool Instance = null;
    
    final String dburl = "jdbc:mysql://"+localhost_connector._HOSTNAME_;
    final String user = localhost_connector._USERNAME_;
    final String pswd = localhost_connector._PASSWORD_;
    
    ArrayList<DBConnection> ConnectionList = new ArrayList();
    
    private ConnectionPool() { // singleton --> private constructor
        this.addConnection(false);
    } // constructor
    
    public static ConnectionPool getInstance() {
        if(Instance == null) Instance = new ConnectionPool();
        return Instance;        
    } // getInstance
    
    // adds and returns a new connection to the pool, flags as in-use if _inUse argument is true
    private Connection addConnection(boolean _inUse) {
        Connection newConnection = null;
        
        try {
            newConnection = DriverManager.getConnection(this.dburl, this.user, this.pswd);
            ConnectionList.add(new DBConnection(newConnection, _inUse));
        } catch(java.sql.SQLException e) {
            System.out.println("SQL-exception while adding connection to pool:");
            System.out.println(e.toString());
        } // try/catch
        
        if(_inUse) return newConnection;
        else return null;
    } // addConnection
    
    // returns a connection from the pool, marking it as in-use
    public Connection getConnection() {
        Connection freeConnection = null;
        
        for(int i = 0; i < this.ConnectionList.size(); i++) {
            
            if(!(this.ConnectionList.get(i).inUse)) { // free connection found
                freeConnection = this.ConnectionList.get(i).Data;
                this.ConnectionList.get(i).inUse = true;
                break;
            } // if
        } // for
        
        if(freeConnection == null) { // no free connection found, just make a new one
            freeConnection = addConnection(true);
        } // if
        
        return freeConnection;
    } // getConnection
    
    // unsets the inUse-marker. Use with caution!
    public void closeConnection(Connection _connection) {
        
        for(int i = 0; i < this.ConnectionList.size(); i++) {
            if(this.ConnectionList.get(i).Data == _connection) { // connection found
                this.ConnectionList.get(i).inUse = false;
                break;
            } // if
        } // for
    } // freeConnection
    
} // class ConnectionPool

/*
 * todo:
 * - check for broken connections when trying to use them
 * - remove connections from pool
 *
 * - 
*/

// String sqlDriver      = "com.mysql.jdbc.Driver";