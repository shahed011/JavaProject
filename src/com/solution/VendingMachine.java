package com.solution;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine implements IVendingMachineMaintenance, IVendingMachineConsumer {
    private ISlotManager _slotManager;
    private ICoinManager _coinManager;


    public VendingMachine(int noOfSlots, List<Double> availableChangeTypes)
    {
        _slotManager = new SlotManager(noOfSlots);
        _coinManager = new CoinManager(availableChangeTypes);
    }

    public int getQuantityOfItemInSlot(int slot) {
        return _slotManager.getItemQuantityInSlot(slot);
    }

    public void setQuantityOfItemInSlot(int slot, int itemQuantity) {
        _slotManager.setItemQuantityInSlot(slot, itemQuantity);
    }

    public double getItemPriceInSlot(int slot) {
        return _slotManager.getSlotItemPrice(slot);
    }

    public void setItemPriceInSlot(int slot, double price) {
        _slotManager.setSlotItemPrice(slot, price);
    }

    public int getAvailableCoinAmountOfOneType(double coinType) {
        return _coinManager.getAvailableAmountOfCoinType(coinType);
    }

    public void setAvailableCoinAmountOfOneType(double coinType, int amount) {
        _coinManager.setAvailableAmountOfCoinType(coinType, amount);
    }

    public List<Double> buyProduct(int slot, List<Double> coins) {
        Double slotPrice = _slotManager.getSlotItemPrice(slot);
        double paidAmount = coins.stream().mapToDouble(Double::doubleValue).sum();

        if (paidAmount < slotPrice) {
            throw new IllegalArgumentException(String.format("Given amount %f is not enough for item in slot %d", paidAmount, slot));
        }

        double calculateChangesFor = paidAmount - slotPrice;
        calculateChangesFor = Math.round(calculateChangesFor*100)/100.0d;

        ChangeResult result = _coinManager.getChangeForSell(calculateChangesFor);

        if (result.is_success()) {
            return result.get_changes();
        }

        return new ArrayList<>();
    }
}