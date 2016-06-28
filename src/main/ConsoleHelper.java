package main;
/**
 * Class for interacting with console.
 */


import exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException //read message from console
    {
        String message = null;
        try
        {
            message = reader.readLine();
            if (message.equalsIgnoreCase("EXIT"))
            {
                throw new InterruptOperationException();
            }

        }
        catch (IOException e){e.printStackTrace();}

        return message;
    }

    public static String askCurrencyCode() throws InterruptOperationException //read currency code from console
    {
        writeMessage(res.getString("choose.currency.code"));
        String temp = readString();


        if (temp.length() == 3)
        {
            return temp.toUpperCase();
        }
        writeMessage(res.getString("invalid.data"));
        return askCurrencyCode().toUpperCase();

    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException //read denomination and count format
    {
        String[] array;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return array;
    }

    static Operation askOperation() throws InterruptOperationException //read number of operation from console
    {
        System.out.println(res.getString("choose.operation"));
        int temp = 0;
        try
        {
            temp = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(temp);

        }catch (IllegalArgumentException e)
        {
            System.out.println(res.getString("invalid.data"));
            return askOperation();
        }

    }

    static void printExitMessage()
    {
        writeMessage(res.getString("the.end"));
    } //say goodbye

}
