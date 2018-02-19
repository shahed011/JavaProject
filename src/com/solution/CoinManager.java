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

    public boolean areCoinTypesAvailable(List<Double> coins) {
        for (Double coin : coins) {
            if (!_coinAmountMap.containsKey(coin)) return false;
        }

        return true;
    }

    public ChangeResult getChangeForSell(double amount) {
        TreeMap<Double, Integer> cloneMap = new TreeMap<>(_coinAmountMap);
        List<Double> changesToReturn = new ArrayList<>();

        for (Double value : _coinAmountMap.descendingMap().keySet()) {

            if (value > amount) continue;

            int availableCoins = _coinAmountMap.get(value);

            for (int i = 1; i <= availableCoins; i++) {
                changesToReturn.add(value);
                _coinAmountMap.put(value, availableCoins - i);

                amount = amount - value;
                amount = Math.round(amount*100)/100.0d;

                if (amount == 0.0 || amount < value) {
                    break;
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
