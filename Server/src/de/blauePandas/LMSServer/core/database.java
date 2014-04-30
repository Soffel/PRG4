

package de.blauePandas.LMSServer.core;

import java.sql.Connection;
//import java.sql.ResultSet; // unused? whut?

/**
 * MySQL-implementation for ConnectionInterface
 * 
 * @author havoc
 */
public class database implements ConnectionInterface {
    
    Connection connection;
    ConnectionPool pool;
    java.sql.PreparedStatement stmt; 

    // would love to get rid of constructor by making prepare and execute static,
    // but would need Java 8 for that, only have Java 7 here
    public database() {
        this.pool = ConnectionPool.getInstance();
        this.connection = null;
        this.stmt = null;
    }

    @Override
    public void getConnection() {
        this.connection = pool.getConnection();
    }
    
    @Override public void prepare(String _Stmt) {
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare
    
    @Override public void prepare(String _Stmt, String[] _args) {
        
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare(String[])

    @Override
    public void prepare(String _Stmt, int _arg) {
        
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _arg);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        } 
    } // prepare(int)

    @Override
    public void prepare(String _Stmt, int[] _args) {
       
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare(int[])
    
    @Override public java.sql.ResultSet execute() {
        
        java.sql.ResultSet results = null;
       
        try {
            results = this.stmt.executeQuery();
        } catch (java.sql.SQLException e) {
            System.out.println("SQL-Error while trying to execute statement \""+this.stmt+"\":");
            System.out.println(e.toString());
        }
        
        return results;     
    
    } // execute
    
    @Override
    public void storeConnection() {
        this.pool.storeConnection(this.connection);
        this.connection = null;
    }

    @Override
    public int shutdown() {
        return this.pool.close();
    }

} // class database
