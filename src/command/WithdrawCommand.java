package command;
/**
 * Command to withdraw some money from account.
 */

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import exception.InterruptOperationException;
import exception.NotEnoughMoneyException;

import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int sum;

        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String txt = ConsoleHelper.readString();
            try
            {
                sum = Integer.parseInt(txt);
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (sum<=0) // check for not empty account
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(sum)) // if needed amount of money available
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try
            {
                manipulator.withdrawAmount(sum);
                ConsoleHelper.writeMessage(res.getString("success.format"));
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            break;

        }
    }
}
