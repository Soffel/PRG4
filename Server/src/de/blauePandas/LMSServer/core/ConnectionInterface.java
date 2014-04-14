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
    public void openConnection();
    
    // prepares a statement (+binds arguments)
    public void prepare(String _Stmt);
    public void prepare(String _Stmt, String[] _args);
    public void prepare(String _Stmt, int arg);
    public void prepare(String _Stmt, int[] args);
    
    // executes the statement
    public String execute();
    
    // puts a no longer needed connection on hold
    public void storeConnection();
} // ConnectionInterface
