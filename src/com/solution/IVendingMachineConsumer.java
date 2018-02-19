package com.solution;

import java.util.List;

public interface IVendingMachineConsumer {
    double getItemPriceInSlot(int slot);
    List<Double> buyProduct(int slot, List<Double> coins);
}
