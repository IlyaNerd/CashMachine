package command;
/**
 * Command to exit from cash machine.
 */

import exception.InterruptOperationException;
import main.CashMachine;
import main.ConsoleHelper;

import java.util.ResourceBundle;

class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));//asking about sure or not to exit from cash machine
        String answer = ConsoleHelper.readString();
        if (answer.equals("yes"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
