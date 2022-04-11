package commands;

/**
 * Класс команды remove_greater_key
 */
public class RemoveGreaterKeyCommand extends Command {
    public RemoveGreaterKeyCommand(String key, String helpText) {
        super(key, helpText);
    }

    @Override
    public void execute(String[] args) {
        try {
            int id = Integer.parseInt(args[0]);
            commandManager.receiver.removeGreaterKey(id);
        } catch (Exception e) {
            System.out.println("Неверное значение ключа. Попробуйте ввести целое число");
        }

    }
}