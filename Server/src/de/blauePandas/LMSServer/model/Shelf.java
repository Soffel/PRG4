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

    public Shelf()
    {
        this.id = 0;
        this.name = "*";
        this.freeYard = 0;
        this.maxLoad = 0;
        this.freeLoad = 0;
    }

    public Shelf(Shelf _copy)
    {
        this.id = _copy.id;
        this.name = _copy.name;
        this.freeYard = _copy.freeYard;
        this.maxLoad = _copy.maxLoad;
        this.freeLoad = _copy.freeLoad;
    }

    public Shelf(int _id, String _name, int _freeYard, int _maxLoad, int _freeLoad)
    {
        this.id = _id;
        this.name = _name;
        this.freeYard = _freeYard;
        this.maxLoad = _maxLoad;
        this.freeLoad = _freeLoad;
    }

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
