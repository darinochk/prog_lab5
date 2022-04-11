package commands;

/**
 * Класс команды filter_contains_name
 */
public class FilterContainsNameCommand extends Command{
    public FilterContainsNameCommand(String key, String helpText) {
        super(key, helpText);
    }

    @Override
    public void execute(String[] args) {
        commandManager.receiver.filterContainsName();
    }
}