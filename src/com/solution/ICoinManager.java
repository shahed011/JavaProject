package com.solution;

import java.util.List;

public interface ICoinManager {
    int getAvailableAmountOfCoinType(double coinType);
    void setAvailableAmountOfCoinType(double coinType, int amount);
    boolean areCoinTypesAvailable(List<Double> coins);
    ChangeResult getChangeForSell(double amount);
}
