package command;
/**
 * Command for getting information about user's account.
 */

import exception.InterruptOperationException;
import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;

import java.util.ResourceBundle;


class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        boolean hasMoney = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        for (CurrencyManipulator cur : CurrencyManipulatorFactory.getAllCurrencyManipulators())
        {
            if (cur.hasMoney())
            {
                ConsoleHelper.writeMessage(cur.getCurrencyCode() + " - " + cur.getTotalAmount());
                hasMoney = true;
            }
        }
        if (!hasMoney)
        {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }


    }
}
