package de.blauePandas.LMSServer.model;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Shelf
{
    private int id;
    private String name;
    private int freeYard;
    private int maxLoad;
    private int freeLoad;


    /**
     * getter
     */
    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getFreeYard()
    {
        return this.freeYard;
    }

    public int getMaxLoad()
    {
        return this.maxLoad;
    }

    public int getFreeLoad() { return this.freeLoad; }

    /**
     * Setter
     */

    public void setId(int _id)
    {
        this.id = _id;
    }

    public void setName(String _name)
    {
        this.name = _name;
    }

    public void setFreeYard(int _freeYard)
    {
        this.freeYard = _freeYard;
    }

    public void setMaxLoad(int _maxLoad)
    {
        this.maxLoad = _maxLoad;
    }

    public void setFreeLoad(int _freeLoad) { this.freeLoad = _freeLoad; }
}
