package tests;

import com.solution.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineMaintenanceTest {
    private IVendingMachineMaintenance _vm;

    @org.junit.jupiter.api.BeforeEach
    private void setUp() {
        ISlotManager _slotManager = new SlotManager(2);
        ICoinManager _coinManager = new CoinManager(Arrays.asList(0.10, 0.20, 1.00));
        _vm = new VendingMachine(_slotManager, _coinManager);
    }

    @org.junit.jupiter.api.Test
    void shouldSetAndGetItemQuantityInSlot() {
        _vm.setItemPriceInSlot(0, 2.5);
        _vm.setQuantityOfItemInSlot(0, 10);

        assertEquals(10, _vm.getQuantityOfItemInSlot(0));
    }

    @org.junit.jupiter.api.Test
    void shouldSetAndGetItemPriceInSlot() {
        _vm.setItemPriceInSlot(0, 5.5);

        assertEquals(5.5, _vm.getItemPriceInSlot(0));
    }

    @org.junit.jupiter.api.Test
    void shouldSetAndReturnAvailableAmountOfCoinsOfOneType() {
        _vm.setAvailableCoinAmountOfOneType(0.1, 100);
        _vm.setAvailableCoinAmountOfOneType(0.2, 50);

        assertEquals(100, _vm.getAvailableCoinAmountOfOneType(0.1));
        assertEquals(50, _vm.getAvailableCoinAmountOfOneType(0.2));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenTryingToSetItemAmountWithoutSettingSlotPrice() {
        assertThrows(IllegalStateException.class, () -> _vm.setQuantityOfItemInSlot(0, 10));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenAskingForInvalidSlot() {
        assertThrows(IllegalArgumentException.class, () -> _vm.setQuantityOfItemInSlot(3, 10));
        assertThrows(IllegalArgumentException.class, () -> _vm.getQuantityOfItemInSlot(3));

        assertThrows(IllegalArgumentException.class, () -> _vm.setItemPriceInSlot(3, 5.5));
        assertThrows(IllegalArgumentException.class, () -> _vm.getItemPriceInSlot(3));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenAskingForInvalidCoinTypeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> _vm.setAvailableCoinAmountOfOneType(3, 100));
        assertThrows(IllegalArgumentException.class, () -> _vm.getAvailableCoinAmountOfOneType(3));
    }
}