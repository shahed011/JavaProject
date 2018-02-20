package tests;

import com.solution.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingMachineConsumeTest {
    private IVendingMachineConsumer _vmConsume;
    private IVendingMachineMaintenance _vmMaintenance;

    @org.junit.jupiter.api.BeforeEach
    private void setUp() {
        ISlotManager _slotManager = new SlotManager(2);
        ICoinManager _coinManager = new CoinManager(Arrays.asList(0.10, 0.20, 1.00));
        _vmMaintenance = new VendingMachine(_slotManager, _coinManager);
        _vmConsume = new VendingMachine((VendingMachine) _vmMaintenance);
    }

    @org.junit.jupiter.api.Test
    void shouldGetPriceFromSlot() {
        _vmMaintenance.setItemPriceInSlot(0, 10);

        assertEquals(10, _vmConsume.getItemPriceInSlot(0));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenQueryingNonExistingSlot() {
        assertThrows(IllegalArgumentException.class, () -> _vmConsume.getItemPriceInSlot(10));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenNotEnoughItemToBuyInSlot() {
        _vmMaintenance.setItemPriceInSlot(0, 0.80);
        _vmMaintenance.setQuantityOfItemInSlot(0, 0);

        assertThrows(IllegalStateException.class, () -> _vmConsume.buyProduct(0, Collections.singletonList(1.00)));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenNonAcceptableCoinsProvided() {
        _vmMaintenance.setItemPriceInSlot(0, 0.80);
        _vmMaintenance.setQuantityOfItemInSlot(0, 10);

        assertThrows(IllegalArgumentException.class, () -> _vmConsume.buyProduct(0, Collections.singletonList(0.80)));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenEnoughMoneyIsNotEntered() {
        _vmMaintenance.setItemPriceInSlot(0, 0.80);
        _vmMaintenance.setQuantityOfItemInSlot(0, 10);

        assertThrows(IllegalArgumentException.class, () -> _vmConsume.buyProduct(0, Collections.singletonList(0.10)));
    }

    @org.junit.jupiter.api.Test
    void successfulBuyGivesRightChanges_ReduceItemByOne_AddUserGivenCoinsToChangeCollection() {
        _vmMaintenance.setItemPriceInSlot(0, 0.80);
        _vmMaintenance.setQuantityOfItemInSlot(0, 10);

        _vmMaintenance.setAvailableCoinAmountOfOneType(0.10, 10);
        _vmMaintenance.setAvailableCoinAmountOfOneType(0.20, 5);

        assertEquals(Arrays.asList(0.2, 0.1), _vmConsume.buyProduct(0, Arrays.asList(1.00, 0.10)));
        assertEquals(10, _vmMaintenance.getAvailableCoinAmountOfOneType(0.10));
        assertEquals(4, _vmMaintenance.getAvailableCoinAmountOfOneType(0.20));
        assertEquals(1, _vmMaintenance.getAvailableCoinAmountOfOneType(1.00));
        assertEquals(9, _vmMaintenance.getQuantityOfItemInSlot(0));

        assertEquals(Collections.singletonList(1.0), _vmConsume.buyProduct(0, Arrays.asList(0.20, 0.20, 0.20, 0.10, 0.10, 1.00)));
        assertEquals(12, _vmMaintenance.getAvailableCoinAmountOfOneType(0.10));
        assertEquals(7, _vmMaintenance.getAvailableCoinAmountOfOneType(0.20));
        assertEquals(1, _vmMaintenance.getAvailableCoinAmountOfOneType(1.00));
        assertEquals(8, _vmMaintenance.getQuantityOfItemInSlot(0));
    }
}
