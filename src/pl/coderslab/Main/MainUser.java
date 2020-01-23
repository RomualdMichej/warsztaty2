package pl.coderslab.Main;

import pl.coderslab.DAO.UserDao;
import pl.coderslab.model.User;

import java.util.Scanner;

public class MainUser {

    private static final String WELCOME_VARIABLE  = " Wybierz jedną z opcji:\n" +
            "\n" +
            "    add – dodanie użytkownika,\n" +
            "    edit – edycja użytkownika,\n" +
            "    delete – usunięcie użytkownika,\n" +
            "    quit – zakończenie programu";
    private static final String ADD = "add";
    private static final String EDIT = "edit";
    private static final String DELETE = "delete";
    private static final String QUIT = "quit";
    private static User user = new User();
    private static UserDao userDao = new UserDao();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(QUIT)) {
            User[] users = userDao.findAll();
            iterateWrite(users);
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

    private static void iterateWrite(User[] users){
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
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

        System.out.printf("Podaj imię nowego urzytkownuka:\n");
        String userName = scanner.next();
        System.out.printf("Podaj email nowego urzytkownuka:\n");
        String userEmail = scanner.next();
        System.out.printf("Podaj hasłol nowego urzytkownuka:\n");
        String userPassword = scanner.next();
        System.out.printf("Podaj ID grupy nowego urzytkownuka:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        int userGroupId = scanner.nextInt();
        user.setUserName(userName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setUser_group_id(userGroupId);
        userDao.create(user);
    }

    private static void edit(Scanner scanner){

        System.out.printf("Podaj ID urzytkownuka do zmiany:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        int userId = scanner.nextInt();
        System.out.printf("Podaj nowe imię urzytkownuka:\n");
        String userName = scanner.next();
        System.out.printf("Podaj nowy email urzytkownuka:\n");
        String userEmail = scanner.next();
        System.out.printf("Podaj nowe hasłol urzytkownuka:\n");
        String userPassword = scanner.next();
        System.out.printf("Podaj nowe ID grupy urzytkownuka:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        int userGroupId = scanner.nextInt();

        user.setId(userId);
        user.setUserName(userName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setUser_group_id(userGroupId);
        userDao.update(user);
    }
    private static void delete(Scanner scanner){

        int input;
        System.out.println("Podaj ID urzytkownika do usunięcia:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        input = scanner.nextInt();
        userDao.delete(input);
    }
}
