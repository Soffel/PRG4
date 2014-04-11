/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.blauePandas.LMSServer.core;

import java.sql.Connection; // failsafe
// import java.sql.ResultSet;

/**
 *
 * @author havoc
 */
public class database implements ConnectionInterface {
    
    Connection connection;
    ConnectionPool pool;
    java.sql.PreparedStatement stmt; 

    // would love to get rid of constructor by making prepare and execute static,
    // but would need Java 8 for that
    public database() {
        this.pool = ConnectionPool.getInstance();
        this.connection = null;
        this.stmt = null;
    }
    
    @Override public void prepare(String _Stmt) {
        
        this.connection = pool.getConnection();
        
        try {
            this.stmt = this.connection.prepareStatement(_Stmt);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare
    
    @Override public void prepare(String _Stmt, String[] _args) {
        
        this.connection = pool.getConnection();
        
        try {
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare(String[])

    @Override
    public void prepare(String _Stmt, int _arg) {
        this.connection = pool.getConnection();
        
        try {
            this.stmt = this.connection.prepareStatement(_Stmt, _arg);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        } 
    } // prepare(int)

    @Override
    public void prepare(String _Stmt, int[] _args) {
        this.connection = pool.getConnection();
        try {
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(java.sql.SQLException e) {
            
            System.out.println("SQL-Error while trying to prepare statement \""+_Stmt+"\":");
            System.out.println(e.toString());
        }
    } // prepare(int[])
    
    
    
    
    // todo
    @Override public String execute() {
        String result;
        java.sql.ResultSet results;
        try {
        results = this.stmt.executeQuery();
        } catch(java.sql.SQLException e) {
        
        }
        this.pool.storeConnection(this.connection);
        this.connection = null;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    } // execute
} // class database
