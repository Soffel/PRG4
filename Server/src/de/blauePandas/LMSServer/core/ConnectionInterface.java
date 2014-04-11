package de.blauePandas.LMSServer.core;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 21.03.2014
 **/


public interface ConnectionInterface {

    // grrr; damn Java 7 won't let me do static interface functions -.-
    
    public void prepare(String _Stmt);
    public void prepare(String _Stmt, String[] _args);
    public void prepare(String _Stmt, int arg);
    public void prepare(String _Stmt, int[] args);
    // no bind function - just pass everything to prepare
    public String execute();
    
} // ConnectionInterface
