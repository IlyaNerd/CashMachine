package command;
/**
 * Deposit command. Give money to cash machine, on the account.
 */

import main.CashMachine;
import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        try
        {
            int dig1 = Integer.parseInt(digits[0]);
            int dig2 = Integer.parseInt(digits[1]);
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
            manipulator.addAmount(dig1, dig2);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), manipulator.getTotalAmount(), code));
        }
        catch (NumberFormatException e)
        {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
