package de.blauePandas.LMSServer.model;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Item
{
    private int id;
    private String name;
    private int weight;
    private boolean hasDate;
/*
    public Item()
    {
        this.id = 0;
        this.name = null;
        this.weight = 0;
        this.hasDate = false;
    }

    public Item(Item _copy)
    {
        this.id = _copy.id;
        this.name = _copy.name;
        this.weight = _copy.weight;
        this.hasDate = _copy.hasDate;
    }

    public Item(int _id, String _name, int _weight, boolean _hasDate)
    {
        this.id = _id;
        this.name = _name;
        this.weight = _weight;
        this.hasDate = _hasDate;
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

    public int getWeight()
    {
        return this.weight;
    }

    public boolean getHasDate()
    {
        return this.hasDate;
    }

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

    public void setWeight(int _weight)
    {
        this.weight = _weight;
    }

    public void setHasDate(boolean _hasDate)
    {
        this.hasDate = _hasDate;
    }
}
