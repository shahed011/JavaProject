package com.solution;

public interface ISlotManager {
    int getItemQuantityInSlot(int slot);
    void setItemQuantityInSlot(int slot, int itemQuantity);
    double getSlotItemPrice(int slot);
    void setSlotItemPrice(int slot, double price);
}
