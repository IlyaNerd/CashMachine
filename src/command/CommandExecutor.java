package command;
/**
 * Executor for all commands in program. (login, exit, deposit, info withdraw)
 */

import main.Operation;
import exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor
{
    private static Map<Operation, Command> map = new HashMap<>();

    static
    {
        map.put(Operation.LOGIN, new LoginCommand());
        map.put(Operation.EXIT, new ExitCommand());
        map.put(Operation.DEPOSIT, new DepositCommand());
        map.put(Operation.INFO, new InfoCommand());
        map.put(Operation.WITHDRAW, new WithdrawCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        map.get(operation).execute();
    }

    private CommandExecutor()
    {
    }
}
