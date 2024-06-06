import java.util.ArrayList;
import java.util.Scanner;

public class Practicum {

    public static void main(String[] args) {
        HamsterFactory hamsterFactory = new HamsterFactory();
        hamsterFactory.start();
    }
}

class HamsterFactory {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Hamster> hamsters = new ArrayList<>();

    public void start() {
        System.out.println("Добро пожаловать на фабрику хомяков!");
        System.out.println("Здесь вы можете создавать, удалять и даже заменять одних хомяков на других");
        System.out.println("Для работы с фабрикой используйте следующие команды:");
        System.out.println("'Печать' — показать всех активных хомяков");
        System.out.println("'Создать [Имя]' — создать хомяка с выбранным именем");
        System.out.println("'Удалить [Индекс]' — удалить хомяка по выбранному индексу");
        System.out.println("'Очистить' — удалить всех, ранее созданных хомяков");
        System.out.println("'Заменить [Индекс] [Имя]' — заменить хомяка под выбранным индексом на нового с выбранным именем");
        System.out.println("'Размер' — вывести на экран текущее количество хомяков в хранилище");
        System.out.println("'Завершить' — завершить работу программы");

        while (true) {
            System.out.println("Введите команду...");

            String command = scanner.nextLine();

            if (command.equals("Завершить")) {
                System.out.println("Программа завершена! Спасибо, что пользуетесь нашей сетью хомячих фабрик");
                break;
            } else {
                executeCommand(command);
            }
        }
    }

    private void executeCommand(@org.jetbrains.annotations.NotNull String command) {
        String[] parts = command.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
            case "печать":
                executePrint();
                break;
            case "создать":
                String name = parts[1];
                executeCreate(name);
                break;
            case "удалить":
                int index = Integer.parseInt(parts[1]);
                executeRemoveByIndex(index);
                break;
            case "очистить":
                executeClear();
                break;
            case "заменить":
                int indexReplace = Integer.parseInt(parts[1]);
                String newName = parts[2];
                executeSet(indexReplace, newName);
                break;
            case "размер":
                executeSize();
                break;
            default:
                showErrorMessage();
                break;
        }
    }

    private void executePrint() {
        System.out.println("Вывожу актуальный список хомяков в хранилище:");
        for (Hamster hamster : hamsters) {
            System.out.println("Хомяк '" + hamster.name + "'");
        }
    }

    private void executeCreate(String name) {
        Hamster newHamster = new Hamster(name);
        hamsters.add(newHamster);
        System.out.println("Хомяк '" + name + "' создан и добавлен в хранилище");
    }

    private void executeRemoveByIndex(int index) {
        if (index >= 0 && index < hamsters.size()) {
            Hamster removedHamster = hamsters.remove(index);
            System.out.println("Хомяк '" + removedHamster.name + "' удалён успешно");
        } else {
            System.out.println("Хомяка по заданному индексу не существует");
        }
    }

    private void executeClear() {
        hamsters.clear();
        System.out.println("Операция очистки завершена успешно! Все хомяки были удалены из хранилища");
    }

    private void executeSet(int index, String name) {
        if (index >= 0 && index < hamsters.size()) {
            Hamster newHamster = new Hamster(name);
            Hamster oldHamster = hamsters.set(index, newHamster);
            System.out.println("Хомяк '" + oldHamster.name + "' был успешно заменён на хомяка '" + name + "'");
        } else {
            System.out.println("В хранилище нет хомяков");
        }
    }

    private void executeSize() {
        if (hamsters.size() > 0) {
            System.out.println("Количество хомяков в хранилище равно " + hamsters.size());
        } else {
            System.out.println("В хранилище нет хомяков");
        }
    }

    private void showErrorMessage() {
        System.out.println("Неверная команда, попробуйте ещё раз.");
    }
}

class Hamster {

    String name;

    public Hamster(String name) {
        this.name = name;
    }
}