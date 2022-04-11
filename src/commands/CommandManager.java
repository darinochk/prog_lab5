package commands;

import collection.Vehicle;
import utill.CollectionManager;
import utill.Parser;

import java.util.*;

/**
 * Класс Менеджер команд(CommandManager). Отвечает за выполнение команд и их хранение
 */
public class CommandManager {
    public Receiver receiver;
    private Scanner consoleScanner;
    private static HashMap<String, Command> commandMap = new HashMap<>();
    private CollectionManager collectionManager;

    /**
     * Конструктор
     * @param scanner добавляет сканнер команд
     * @param collectionManager добавляет упрвление коллекцией
     */
    public CommandManager(Scanner scanner, CollectionManager collectionManager) {
        this.receiver = new Receiver();
        this.consoleScanner = scanner;
        this.collectionManager = collectionManager;
        addCommands(
                new HelpCommand("help"," - вывести справку по доступным командам"),
                new InfoCommand("info"," - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)"),
                new ShowCommand("show"," - вывести в стандартный поток вывода все элементы коллекции в строковом представлении"),
                new InsertCommand("insert"," - добавить новый элемент с заданным ключом"),
                new UpdateCommand("update"," - обновить значение элемента коллекции, id которого равен заданному"),
                new RemoveKeyCommand("remove_key"," - удалить элемент из коллекции по его ключу"),
                new ClearCommand("clear"," - очистить коллекцию"),
                new SaveCommand("save"," - сохранить коллекцию в файл"),
                new ExecuteScriptCommand("execute_script"," - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме."),
                new ExitCommand("exit"," - завершить программу (без сохранения в файл)"),
                new RemoveGreaterCommand("remove_greater"," - удалить из коллекции все элементы, превышающие заданный"),
                new ReplaceIfGreaterCommand("replace_if_greater"," - заменить значение по ключу, если новое значение больше старого"),
                new RemoveGreaterKeyCommand("remove_greater_key"," - удалить из коллекции все элементы, ключ которых превышает заданный"),
                new RemoveAnyByFuelCommand("remove_any_by_fuel"," - удалить из коллекции один элемент, значение поля fuelConsumption которого эквивалентно заданному"),
                new FilterByEnginePowerCommand("filter_by_engine_power"," - вывести элементы, значение поля enginePower которых равно заданному"),
                new FilterContainsNameCommand("filter_contains_name"," - вывести элементы, значение поля name которых содержит заданную подстроку")
        );
    }

    /**
     * Метод, запускающий выполение команды
     * @param commandKey ключ команды
     * @param args аргументы команды
     */
    public void runCommand(String commandKey, String [] args){
        if (!commandMap.containsKey(commandKey)) {
            System.out.println("Такой команды не существует. Введите команду help для просмотра существующих команд");
        }
        else {
            commandMap.get(commandKey).execute(args);
        }
    }

    /**
     * Метод, добавляющицй все команды в хэщ-таблицу
     * @param commands команды
     */
    private void addCommands(Command ... commands) {
        for (Command command : commands) {
            commandMap.put(command.getKey(), command);
            command.setCommandManager(this);
        }
    }

    public Collection<Command> getAllCommands() {
        return commandMap.values();
    }

    public static ArrayList<String> getArrayOfCommands() {
        return new ArrayList<String>(commandMap.keySet());
    }

    /**
     * Класс Receiver(получатель). Здесь описана логика выполняемых команд
     */
    public class Receiver {

        public void help() {
            System.out.println("Доступные команды:");
            getAllCommands().stream().map(command -> command.getKey() + command.getHelpText()).forEach(System.out::println);
            System.out.println();
            System.out.println(getArrayOfCommands());
        }

        public void info() {
            for (Map.Entry<Integer,Vehicle> e : collectionManager.getCollection().entrySet()) {
                if (e.getValue().equals(null)) {
                    System.out.println("Ошибка");
                    System.exit(1);
                }
            }

            System.out.println("Тип коллекции: " + collectionManager.getCollectionType());
            System.out.println("Дата инициализации: " + collectionManager.getInitDate());
            System.out.println("Количество элементов: " + collectionManager.getNumberOfElements());
        }

        public void show() {
            collectionManager.show();
        }

        public void insert(int id) {
            collectionManager.insert(id);
        }

        public void update(int id) {
            collectionManager.update(id);
        }

        public void removeKey(int id) {
            collectionManager.removeKey(id);
        }

        public void clear() {
            collectionManager.clear();
        }

        public void save() {
            collectionManager.save();
        }

        public void executeScript(String filename) {
            collectionManager.executeScript(Parser.readScript(filename), Parser.returnScriptPaths(filename));
            Parser.listOfPaths.clear();
        }

        public void exit() {
            System.out.println("Завершение работы программы ...");
            System.exit(1);
        }

        public void removeGreater() {
            collectionManager.removeGreater();
        }

        public void replaceIfGreater(int id) {
            collectionManager.replaceIfGreater(id);
        }

        public void removeGreaterKey(int id) {
            collectionManager.removeGreaterKey(id);
        }

        public void removeAnyByFuel() {
            collectionManager.removeAnyByFuel();
        }

        public void filterByEnginePower() {
            collectionManager.filterByEnginePower();
        }

        public void filterContainsName() {
           collectionManager.filterContainsName();
        }
    }
}
