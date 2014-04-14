

package de.blauePandas.LMSServer.core;

import java.sql.Connection;

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
    public void openConnection() {
        this.connection = pool.getConnection();
    }
    
    @Override public void prepare(String _Stmt) {
        
        this.stmt = null;
        
        try {
            this.stmt = this.connection.prepareStatement(_Stmt);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare
    
    @Override public void prepare(String _Stmt, String[] _args) {
        
        this.stmt = null;
        
        try {
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
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare(int[])
    
    
    
    
    // todo
    @Override public String[] execute() {
        
        String result;
        java.sql.ResultSet results;
       
        try {
            this.stmt.execute();
            results = this.stmt.getResultSet();
        } catch (java.sql.SQLException e) {
            System.out.println("SQL-Error while trying to execute statement \""+this.stmt+"\":");
            System.out.println(e.toString());
        }
        
        // todo: loop through resultset to get output
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    } // execute

    @Override
    public void storeConnection() {
        this.pool.storeConnection(this.connection);
        this.connection = null;
    }
    
    
} // class database
