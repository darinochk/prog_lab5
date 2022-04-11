package commands;

/**
 * Класс команды filter_by_engine_power
 */
public class FilterByEnginePowerCommand extends Command{
    public FilterByEnginePowerCommand(String key, String helpText) {
        super(key, helpText);
    }

    @Override
    public void execute(String[] args) {
        commandManager.receiver.filterByEnginePower();
    }
}
