package com.solution;

public class Slot implements ISlot {
    private int _noOfItems;
    private double _price;

    public int get_noOfItems() {
        return _noOfItems;
    }

    public void set_noOfItems(int _noOfItems) {
        this._noOfItems = _noOfItems;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public boolean isPriceSet()
    {
        return !(_price == 0.0f);
    }
}