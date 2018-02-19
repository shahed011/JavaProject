package com.solution;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20, 1.00));

        System.out.println(vm.getQuantityOfItemInSlot(0));
        System.out.println(vm.getItemPriceInSlot(0));
        System.out.println();

        vm.setItemPriceInSlot(0, 5);
        vm.setItemPriceInSlot(1, 0.80);

        vm.setQuantityOfItemInSlot(0, 10);
        vm.setQuantityOfItemInSlot(1, 5);

        vm.setAvailableCoinAmountOfOneType(0.10, 10);
        vm.setAvailableCoinAmountOfOneType(0.20, 5);

        System.out.println(vm.getQuantityOfItemInSlot(0));
        System.out.println(vm.getItemPriceInSlot(0));

        System.out.println(vm.getQuantityOfItemInSlot(1));
        System.out.println(vm.getItemPriceInSlot(1));

        System.out.println(vm.buyProduct(1, Arrays.asList(1.10)));
    }
}
