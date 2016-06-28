package main;
/**
 * Class for manipulations with currencies.
 */

import exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) // add money to cash machine by denomination
    {
        if (denominations.keySet().contains(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() // get amount of money on the account
    {
        int result = 0;
        for (Map.Entry<Integer, Integer> map : denominations.entrySet())
        {
            result = result + map.getValue()*map.getKey();
        }
        return result;
    }

    public boolean hasMoney() // check for emptiness of account
    {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) // check for needed amount of money for withdraw
    {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException // withdraw amount of money from cash machine
    {
        int sum = expectedAmount;
        HashMap<Integer, Integer> temp = new HashMap<>();
        temp.putAll(denominations);
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            list.add(pair.getKey());

        Collections.sort(list);
        Collections.reverse(list);

        TreeMap<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o2.compareTo(o1);
            }
        });

        for (Integer aList : list) {
            int key = aList;
            int value = temp.get(key);
            while (true) {
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }

        if (sum > 0)
            throw new NotEnoughMoneyException();
        else
        {
            for (Map.Entry<Integer, Integer> pair : result.entrySet())
                ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());

            denominations.clear();
            denominations.putAll(temp);
        }
        return result;
    }
}
