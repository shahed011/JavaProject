package com.solution;

public interface IVendingMachineMaintenance {
    int getQuantityOfItemInSlot(int slot);
    void setQuantityOfItemInSlot(int slot, int itemQuantity);
    double getItemPriceInSlot(int slot);
    void setItemPriceInSlot(int slot, double price);
    int getAvailableCoinAmountOfOneType(double coinType);
    void setAvailableCoinAmountOfOneType(double coinType, int amount);
}
