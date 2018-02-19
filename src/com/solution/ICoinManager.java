package com.solution;

public interface ICoinManager {
    int getAvailableAmountOfCoinType(double coinType);
    void setAvailableAmountOfCoinType(double coinType, int amount);
    ChangeResult getChangeForSell(double amount);
}
