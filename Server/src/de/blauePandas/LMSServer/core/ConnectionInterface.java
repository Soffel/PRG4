package de.blauePandas.LMSServer.core;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 21.03.2014
 **/


public interface ConnectionInterface {

    // grrr; damn Java 7 won't let me do static interface functions -.-
    
    // opens a connection

    /**
     * opens the connection to the database
     */
        public void openConnection();
    
    // prepares a statement (+binds arguments)

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
     * @return the result of the db-query as a string
     */
    public String[] execute();
    
    /**
     * puts a (atm) no longer needed connection on "hold"
     */
    public void storeConnection();
} // ConnectionInterface
