package de.blauePandas.LMSServer.core;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 21.03.2014
 **/


public interface ConnectInterface
{
    /**
     *Datenbankzugriff ohne rückgabe (update, insert, delete)
     **/
    public void doThis(String _sql, String[] _args)throws Exception;

    /**
     *Datenbankzugriff mit rückgabe (insert)
     **/
    public ResultSet doThisAndResult(String _sql, String[] _args)throws Exception;
}
