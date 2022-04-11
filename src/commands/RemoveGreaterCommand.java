package commands;

/**
 * Класс команды remove_greater
 */
public class RemoveGreaterCommand extends Command{
    public RemoveGreaterCommand(String key, String helpText) {
        super(key, helpText);
    }

    @Override
    public void execute(String[] args) {
        commandManager.receiver.removeGreater();
    }
}