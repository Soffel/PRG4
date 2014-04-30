package de.blauePandas.LMSServer.core;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 21.03.2014
 **/


public interface ConnectionInterface {

    /**
     * fetches a connection to the database from the pool
     */
        public void getConnection();

    /**
     * prepares a statement for the database
     * 
     * @param _Stmt the statement to be prepared
     */
    public void prepare(String _Stmt);
    public void prepare(String _Stmt, String[] _args);
    public void prepare(String _Stmt, int arg);
    public void prepare(String _Stmt, int[] args);
    
    /**
     * 
     * @return the result of the db-query as a ResultSet
     */
    public java.sql.ResultSet execute();
    
    /**
     * puts a (atm) no longer needed connection on "hold in the pool
     */
    public void storeConnection();
    
    /**
     * force-close all connections in pool,
     * @return number of in-use-connections closed
     */
    public int shutdown();
} // ConnectionInterface
