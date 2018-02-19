package com.solution;

import java.util.*;

public class CoinManager implements ICoinManager {
    private TreeMap<Double, Integer> _coinAmountMap;

    public CoinManager(List<Double> availableCoins) {
        _coinAmountMap = new TreeMap<>();

        for (Double change : availableCoins)
        {
            _coinAmountMap.put(change, 0);
        }
    }

    public int getAvailableAmountOfCoinType(double coinType) {
        validateCoinType(coinType);

        return _coinAmountMap.get(coinType);
    }

    public void setAvailableAmountOfCoinType(double coinType, int amount) {
        validateCoinType(coinType);

        _coinAmountMap.put(coinType, amount);
    }

    public ChangeResult getChangeForSell(double amount) {
        TreeMap<Double, Integer> cloneMap = new TreeMap<>(_coinAmountMap);
        List<Double> changesToReturn = new ArrayList<>();

        for (Double value : _coinAmountMap.descendingMap().keySet()) {

            int numCoins = (int) (amount / value);

            if (numCoins > 0) {
                int old = _coinAmountMap.get(value);
                if (numCoins < old) {
                    _coinAmountMap.put(value, old - numCoins);

                    for (int i=0; i<numCoins; i++) {
                        changesToReturn.add(value);
                    }

                    amount = amount % value;
                    amount = Math.round(amount*100)/100.0d;
                }
            }

            if (amount == 0.0) {
                break;
            }
        }

        if (amount == 0.0) {
            return new ChangeResult(true, changesToReturn);
        }
        else
        {
            _coinAmountMap = cloneMap;
            return new ChangeResult(false, new ArrayList<>());
        }
    }

    private void validateCoinType(double coinType)
    {
        if (!_coinAmountMap.containsKey(coinType)) {
            throw new IllegalArgumentException(String.format("Coin of %f is not supported", coinType));
        }
    }
}
