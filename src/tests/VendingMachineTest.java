package tests;

import com.solution.VendingMachine;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {


    @org.junit.jupiter.api.Test
    void shouldSetAndGetItemQuantityInSlot() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));
        vm.setItemPriceInSlot(0, 2.5);
        vm.setQuantityOfItemInSlot(0, 10);

        assertEquals(10, vm.getQuantityOfItemInSlot(0));
    }

    @org.junit.jupiter.api.Test
    void shouldSetAndGetItemPriceInSlot() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));
        vm.setItemPriceInSlot(0, 5.5);

        assertEquals(5.5, vm.getItemPriceInSlot(0));
    }

    @org.junit.jupiter.api.Test
    void shouldSetAndReturnAvailableAmountOfCoinsOfOneType() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));
        vm.setAvailableCoinAmountOfOneType(0.1, 100);
        vm.setAvailableCoinAmountOfOneType(0.1, 50);

        assertEquals(100, vm.getAvailableCoinAmountOfOneType(0.1));
        assertEquals(50, vm.getAvailableCoinAmountOfOneType(0.2));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenTryingToSetItemAmountWithoutSettingSlotPrice() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));

        assertThrows(IllegalStateException.class, () -> vm.setQuantityOfItemInSlot(0, 10));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenAskingForInvalidSlot() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));

        assertThrows(IllegalArgumentException.class, () -> vm.setQuantityOfItemInSlot(3, 10));
        assertThrows(IllegalArgumentException.class, () -> vm.getQuantityOfItemInSlot(3));

        assertThrows(IllegalArgumentException.class, () -> vm.setItemPriceInSlot(3, 5.5));
        assertThrows(IllegalArgumentException.class, () -> vm.getItemPriceInSlot(3));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenAskingForInvalidCoinTypeQuantity() {
        VendingMachine vm =  new VendingMachine(2, Arrays.asList(0.10, 0.20));

        assertThrows(IllegalArgumentException.class, () -> vm.setAvailableCoinAmountOfOneType(3, 100));
        assertThrows(IllegalArgumentException.class, () -> vm.getAvailableCoinAmountOfOneType(3));
    }
}