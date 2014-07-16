package de.blauePandas.LMSServer.core.dao;

import de.blauePandas.LMSServer.model.Person;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class PersonDAO implements DAOInterface<Person>
{
    @Override
    public boolean insert(Person _t)
    {
        return false;
    }

    @Override
    public Person select(Person _t)
    {
        return null;
    }

    @Override
    public boolean update(Person _t)
    {
        return false;
    }

    @Override
    public boolean delete(Person _t)
    {
        return false;
    }
}
