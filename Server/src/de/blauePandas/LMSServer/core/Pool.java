package de.blauePandas.LMSServer.core;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 04.03.14
 */

class Pool
{
    Connection  connection  = null;
    boolean     inUse       = false;

    public Pool(Connection _connection, boolean _inUse)
    {
        this.connection = _connection;
        this.inUse      = _inUse;
    }
}