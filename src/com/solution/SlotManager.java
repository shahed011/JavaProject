package com.solution;

import java.util.HashMap;

public class SlotManager implements ISlotManager {
    private HashMap<Integer, Slot> _slotStatus;

    public SlotManager(int numberOfSlots) {
        _slotStatus = new HashMap<>();

        for (int i=0; i<numberOfSlots; i++){
            _slotStatus.put(i, new Slot());
        }
    }

    public int getItemQuantityInSlot(int slot) {
        validateSlot(slot);

        return _slotStatus.get(slot).get_noOfItems();
    }

    public void setItemQuantityInSlot(int slot, int itemQuantity) {
        validateSlot(slot);

        Slot slotObject = _slotStatus.get(slot);
        validateSlotPrice(slotObject, slot);

        slotObject.set_noOfItems(itemQuantity);
    }

    public double getSlotItemPrice(int slot) {
        validateSlot(slot);

        return _slotStatus.get(slot).get_price();
    }

    public void setSlotItemPrice(int slot, double price) {
        validateSlot(slot);

        _slotStatus.get(slot).set_price(price);
    }

    private void validateSlot(int slot)
    {
        if (!_slotStatus.containsKey(slot)) {
            throw new IllegalArgumentException(String.format("Slot %d does not exists", slot));
        }
    }

    private void validateSlotPrice(Slot slotObject, int slot)
    {
        if (!slotObject.isPriceSet()) {
            throw new IllegalStateException(String.format("Slot %d does not have a price set", slot));
        }
    }
}
