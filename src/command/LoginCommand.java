package command;
/**
 * Command for sing in. Authorization.
 */



import exception.InterruptOperationException;
import main.CashMachine;
import main.ConsoleHelper;

import java.util.ResourceBundle;


public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();

            if (!(number.length()==12&&pin.length()==4)) //check for length of number of card and pin to it
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            try
            {
                int a = Integer.parseInt(pin);
                Long b = Long.parseLong(number);
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            if (validCreditCards.containsKey(number)&&validCreditCards.getString(number).equals(pin))
            {
                ConsoleHelper.writeMessage(res.getString("success.format"));
                break;
            }
            else
            {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
            }
        }

    }
}
