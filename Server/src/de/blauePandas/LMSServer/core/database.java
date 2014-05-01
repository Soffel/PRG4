

package de.blauePandas.LMSServer.core;

import de.blauePandas.LMSServer.control.TextFileWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQL-implementation for ConnectionInterface
 * 
 * @author havoc
 */
public class database implements ConnectionInterface {
    
    Connection connection;
    ConnectionPool pool;
    PreparedStatement stmt;

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
        } catch(SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
    } // prepare
    
    @Override public void prepare(String _Stmt, String[] _args) {
        
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
    } // prepare(String[])

    @Override
    public void prepare(String _Stmt, int _arg) {
        
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _arg);
        } catch(SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        } 
    } // prepare(int)

    @Override
    public void prepare(String _Stmt, int[] _args) {
       
        this.stmt = null;
        
        try {
            this.connection.setAutoCommit(false);
            this.stmt = this.connection.prepareStatement(_Stmt, _args);
        } catch(SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
    } // prepare(int[])
    
    @Override public ResultSet execute() {
        
        ResultSet results = null;
       
        try {
            results = this.stmt.executeQuery();
        } catch (SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
        
        return results;     
    
    } // execute
    
    @Override
    public void storeConnection() {
        
        try {
            if(this.stmt != null) this.stmt.close();
        } catch (SQLException e) {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
        
        this.pool.storeConnection(this.connection);
        this.connection = null;
    }

    @Override
    public int shutdown() {
        return this.pool.close();
    }

} // class database
