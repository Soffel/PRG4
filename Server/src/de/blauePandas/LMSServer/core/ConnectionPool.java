package de.blauePandas.LMSServer.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author havoc
 * 
 * manages database-connections, provides methods for access by database
 */

public class ConnectionPool {
    
    static ConnectionPool OpenInstance = null;
    String sqlDriver = "com.mysql.jdbc.Driver";
    final String dburl = "jdbc:mysql://"+localhost_connector._HOSTNAME_;
    final String user = localhost_connector._USERNAME_;
    final String pswd = localhost_connector._PASSWORD_;
     
    // mein Compiler will Variante 1, der von Andre Variante 2.
    // kommentiert einfach um wies eurem compiler gefällt.
    
    ArrayList<DBConnection> ConnectionList = new ArrayList();
    //ArrayList<DBConnection> ConnectionList = new ArrayList<DBConnection>();
    
    /**
     * constructor private - use ConnectionPool.getInstance(); 
     */
    private ConnectionPool() {
        this.addConnection(false);
    } // constructor
    
    /**
     * creates a ConnectionPool if no open one is there,
     * returns it (or the open one that was there)
     * 
     * @return an open ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if(OpenInstance == null) OpenInstance = new ConnectionPool();
        return OpenInstance;
    } // getInstance
    
    /**
     * force-closes all connections in the pool (for shutdown etc.)
     * caution: also closes in-use connections
     * @return number of in-use connections closed
     */
    public int close() {
        int openConnections = 0;
        for(int i = 0; i < this.ConnectionList.size(); i++) {
            try {
                DBConnection con = this.ConnectionList.get(i);
                con.Data.close();
                if(con.inUse) openConnections++;
                this.ConnectionList.remove(i);
            }
            catch (java.sql.SQLException e) {
                System.out.println("Error while closing Connectionpool: ");
                System.out.println(e.toString());
            }
        }
        ConnectionPool.OpenInstance = null;
        return openConnections;
    }
    
    /**
     * adds a new connection to the pool, 
     * flags as in-use if _inUse argument is true
     * 
     * @return the added connection if _inUse = true; else null
     * @param _inUse true if you want to directly use (&return) the connection
     */
     private Connection addConnection(boolean _inUse) {
        Connection newConnection = null;

         try {  // load driver
             Class.forName(this.sqlDriver);
         } catch(ClassNotFoundException e) {
             System.out.println("Error while loading database driver:");
             System.out.println(e.toString());
         }

        try {
            // add connection
            newConnection = DriverManager.getConnection(this.dburl, this.user, this.pswd);
            ConnectionList.add(new DBConnection(newConnection, _inUse));
        } catch(java.sql.SQLException e) {
            System.out.println("SQL-exception while adding connection to pool:");

            System.out.println(e.toString());
        }
        
        if(_inUse) return newConnection;
        else return null;
    } // addConnection
    
    /**
     * picks an unused connection from the pool,
     * marks it as in-use
     * 
     * @return a connection
     */
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
    
    /**
     * unsets the inUse-marker. 
     * Use with caution!
     * Be sure to remove all pointers to the connection!
     * 
     * @param _connection the connection to be stored
     */
    public void storeConnection(Connection _connection) {
        
        for(int i = 0; i < this.ConnectionList.size(); i++) {
            if(this.ConnectionList.get(i).Data == _connection) { // connection found
                this.ConnectionList.get(i).inUse = false;
                break;
            } // if
        } // for
    } // closeConnection
    
} // class ConnectionPool

/*
 * todo:
 * - check for broken connections when trying to use them
 * - close allConnections - funktion :P (aw)
 * - use Server "Errorlog" :P (aw)
*/

// String sqlDriver      = "com.mysql.jdbc.Driver";