package commands;

/**
 * Класс команды remove_any_by_fuel
 */
public class RemoveAnyByFuelCommand extends Command{
    public RemoveAnyByFuelCommand(String key, String helpText) {
        super(key, helpText);
    }

    @Override
    public void execute(String[] args) {
        commandManager.receiver.removeAnyByFuel();
    }
}
