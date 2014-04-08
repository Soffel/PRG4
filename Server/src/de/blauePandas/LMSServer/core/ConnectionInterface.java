package de.blauePandas.LMSServer.core;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 21.03.2014
 **/


public interface ConnectionInterface {

    public void prepare(String _Stmt);
    public void bind(int i, String _param);
    public String execute();
    
} // ConnectionInterface
