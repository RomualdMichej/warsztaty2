package pl.coderslab.Main;

import pl.coderslab.DAO.GroupDao;
import pl.coderslab.model.Group;

import java.util.Scanner;

public class MainGroup {

    private static final String WELCOME_VARIABLE  = "Wybierz jedną z opcji:\n" +
            "\n" +
            "    add – dodanie grupy,\n" +
            "    edit – edycja grupy,\n" +
            "    delete – usunięcie grupy,\n" +
            "    quit – zakończenie programu.";
    private static final String ADD = "add";
    private static final String EDIT = "edit";
    private static final String DELETE = "delete";
    private static final String QUIT = "quit";
    private static GroupDao groupDao = new GroupDao();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(QUIT)) {
            Group[] groups = groupDao.findAll();
            iterateWrite(groups);
            System.out.printf("\n" + WELCOME_VARIABLE + "\n");

            input = check(scanner);
            if (input.equals(ADD)) {
                add(scanner);
            } else if (input.equals(EDIT)) {
                edit(scanner);
            } else if (input.equals(DELETE)) {
                delete(scanner);
            }
        }
    }

    private static void iterateWrite(Group[] groups){
        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i]);
        }
    }

    private static String check(Scanner scanner) {
        String input = "";
        while (!input.equals(ADD) && !input.equals(EDIT)
                && !input.equals(DELETE) && !input.equals(QUIT)) {
            input = scanner.next();
            if (!input.equals(ADD) && !input.equals(EDIT)
                    && !input.equals(DELETE) && !input.equals(QUIT)) {
                System.out.println("Nieprawidłowe dane. Podaj jeszcze raz:");
            }
        }
        return input;
    }
    private static void add(Scanner scanner){

        System.out.printf("Podaj nową nazwę grupy:\n");
        String groupName = scanner.next();

        Group group = new Group(groupName);
        groupDao.create(group);
    }

    private static void edit(Scanner scanner){

        System.out.printf("Podaj ID grupy do zmiany:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        int groupId = scanner.nextInt();
        System.out.printf("Podaj nowyą nazwę grupy:\n");
        String groupName = scanner.next();

        Group group = new Group(groupName);
        group.setId(groupId);
        groupDao.update(group);
    }
    private static void delete(Scanner scanner){

        int input;
        System.out.println("Podaj ID grupy do usunięcia:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        input = scanner.nextInt();
        groupDao.delete(input);
    }
}

