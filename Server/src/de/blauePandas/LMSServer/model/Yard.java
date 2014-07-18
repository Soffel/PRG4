package de.blauePandas.LMSServer.model;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Yard
{
    private int id;
    private int itemId;
    private int shelfId;
    private int numberOfItems;
    private int posHori;
    private int posVerti;
    private boolean occupied;


    /**
     * getter
     */
    public int getId()
    {
        return this.id;
    }

    public int getItemId()
    {
        return this.itemId;
    }

    public int getShelfId()
    {
        return this.shelfId;
    }

    public int getNumberOfItems()
    {
        return this.numberOfItems;
    }

    public int getPosHori() { return this.posHori; }

    public int getPosVerti() { return this.posVerti; }

    public boolean getoccupied() { return this.occupied; }

    /**
     * Setter
     */

    public void setId(int _id)
    {
        this.id = _id;
    }

    public void setItemId(int _itemId)
    {
        this.itemId = _itemId;
    }

    public void setShelfId(int _shelfId)
    {
        this.shelfId = _shelfId;
    }

    public void setNumberOfItems(int _numberOfItems)
    {
        this.numberOfItems = _numberOfItems;
    }

    public void setPosHori(int _posHori) { this.posHori = _posHori; }

    public void setPosVerti(int _posVerti) { this.posVerti = _posVerti; }

    public void setOccupied(boolean _occupied) { this.occupied = _occupied; }

}
