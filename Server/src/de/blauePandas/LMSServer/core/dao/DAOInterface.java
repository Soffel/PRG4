package de.blauePandas.LMSServer.core.dao;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public interface DAOInterface <T>
{
    public boolean insert(T _t);
    public T       select(T _t);
    public boolean update(T _t);
    public boolean delete(T _t);
}

