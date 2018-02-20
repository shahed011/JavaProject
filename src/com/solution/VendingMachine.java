package com.solution;

import java.util.List;

public class VendingMachine implements IVendingMachineMaintenance, IVendingMachineConsumer {
    private ISlotManager _slotManager;
    private ICoinManager _coinManager;

    public VendingMachine(ISlotManager slotManager, ICoinManager coinManager)
    {
        _slotManager = slotManager;
        _coinManager = coinManager;
    }

    public VendingMachine(VendingMachine vm) {
        _slotManager = vm._slotManager;
        _coinManager = vm._coinManager;
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
        if (_slotManager.getItemQuantityInSlot(slot) <= 0) {
            throw new IllegalStateException(String.format("No item left in slot %d", slot));
        }

        if (!_coinManager.areCoinTypesAvailable(coins)) {
            throw new IllegalArgumentException("One more more provided coins are not accepted");
        }

        Double slotPrice = _slotManager.getSlotItemPrice(slot);
        double paidAmount = coins.stream().mapToDouble(Double::doubleValue).sum();

        if (paidAmount < slotPrice) {
            throw new IllegalArgumentException(String.format("Given amount %f is not enough for item in slot %d", paidAmount, slot));
        }

        double calculateChangesFor = paidAmount - slotPrice;
        calculateChangesFor = Math.round(calculateChangesFor*100)/100.0d;

        ChangeResult result = _coinManager.getChangeForSell(calculateChangesFor);

        if (result.is_success()) {
            updateMachineState(slot, coins);

            return result.get_changes();
        }

        return coins;
    }

    private void updateMachineState(int slotToRemoveItemFrom, List<Double> coinsToadd) {
        decreaseItemByOneFromSlot(slotToRemoveItemFrom);
        addUserGivenCoinsToMachine(coinsToadd);
    }

    private void decreaseItemByOneFromSlot(int slot) {
        int numberOfItemInSlot =_slotManager.getItemQuantityInSlot(slot);
        _slotManager.setItemQuantityInSlot(slot, numberOfItemInSlot - 1);
    }

    private void addUserGivenCoinsToMachine(List<Double> coins) {
        for (Double coin : coins) {
            int currentAmount = _coinManager.getAvailableAmountOfCoinType(coin);
            _coinManager.setAvailableAmountOfCoinType(coin, currentAmount + 1);
        }
    }
}