package pl.coderslab.Main;

import pl.coderslab.DAO.ExerciseDao;
import pl.coderslab.DAO.SolutionDao;
import pl.coderslab.DAO.UserDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.util.List;
import java.util.Scanner;

public class MainSolution {

    private static final String WELCOME_VARIABLE  = "Wybierz jedną z opcji:\n" +
            "\n" +
            "    add – przypisywanie zadań do użytkowników,\n" +
            "    view – przeglądanie rozwiązań danego użytkownika,\n" +
            "    quit – zakończenie programu.";
    private static final String ADD = "add";
    private static final String VIEW = "view";
    private static final String QUIT = "quit";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(QUIT)) {
            System.out.printf("\n" + WELCOME_VARIABLE + "\n");

            input = check(scanner);
            if (input.equals(ADD)) {
                add(scanner);
            } else if (input.equals(VIEW)) {
                view(scanner);
            }
        }
    }

    private static String check(Scanner scanner) {
        String input = "";
        while (!input.equals(ADD) && !input.equals(VIEW) && !input.equals(QUIT)) {
            input = scanner.next();
            if (!input.equals(ADD) && !input.equals(VIEW) && !input.equals(QUIT)) {
                System.out.println("Nieprawidłowe dane. Podaj jeszcze raz:");
            }
        }
        return input;
    }

    private static void iterateWriteUser(User[] users){
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
    }

    private static void iterateWriteExercise(Exercise[] exercises){
        for (int i = 0; i < exercises.length; i++) {
            System.out.println(exercises[i]);
        }
    }

    private static void intCheck(Scanner scanner){

        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
    }

    private static void add(Scanner scanner){

        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        User[] users = userDao.findAll();
        iterateWriteUser(users);
        System.out.println("\nPodaj ID urzytkownika, dla którego chcesz utworzyć" +
                " rozwiązanie:\n");
        intCheck(scanner);
        int userId = scanner.nextInt();
        Exercise[] exercises = exerciseDao.findAll();
        iterateWriteExercise(exercises);
        System.out.println("\nPodaj ID zdania, dla którego chcesz utworzyć" +
                " rozwiązanie:\n");
        intCheck(scanner);
        int exerciseId = scanner.nextInt();

        Solution solution = new Solution("",exerciseId, userId);
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
    }

    private static void view(Scanner scanner){

        System.out.println("\nPoda ID urzytkownika:\n");
        intCheck(scanner);
        int userId = scanner.nextInt();
        SolutionDao solutionDao = new SolutionDao();
        List<Solution> list = solutionDao.findAllByUserId(userId);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
