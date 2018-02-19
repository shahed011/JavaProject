package com.solution;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ISlotManager slotManager = new SlotManager(2);
        ICoinManager coinManager = new CoinManager(Arrays.asList(0.10, 0.20, 1.00));

        // Machine maintenance
        IVendingMachineMaintenance vmMaintenance =  new VendingMachine(slotManager, coinManager);

        System.out.println(vmMaintenance.getQuantityOfItemInSlot(0));
        System.out.println(vmMaintenance.getItemPriceInSlot(0));
        System.out.println();

        vmMaintenance.setItemPriceInSlot(0, 5);
        vmMaintenance.setItemPriceInSlot(1, 0.80);
        System.out.println();

        vmMaintenance.setQuantityOfItemInSlot(0, 10);
        vmMaintenance.setQuantityOfItemInSlot(1, 5);
        System.out.println();

        vmMaintenance.setAvailableCoinAmountOfOneType(0.10, 10);
        vmMaintenance.setAvailableCoinAmountOfOneType(0.20, 5);

        System.out.println(vmMaintenance.getQuantityOfItemInSlot(0));
        System.out.println(vmMaintenance.getItemPriceInSlot(0));
        System.out.println();

        System.out.println(vmMaintenance.getQuantityOfItemInSlot(1));
        System.out.println(vmMaintenance.getItemPriceInSlot(1));
        System.out.println();

        // Machine at user service
        IVendingMachineConsumer vmConsume = new VendingMachine((VendingMachine) vmMaintenance);

        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.10));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.20));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(1.00));
        System.out.println();

        System.out.println(vmConsume.buyProduct(1, Arrays.asList(1.00, 0.10)));

        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.10));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.20));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(1.00));
        System.out.println();

        System.out.println(vmConsume.buyProduct(1, Arrays.asList(0.20, 0.20, 0.20, 0.10, 0.10, 1.00)));

        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.10));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(0.20));
        System.out.println(vmMaintenance.getAvailableCoinAmountOfOneType(1.00));
        System.out.println();
    }
}
