package pl.coderslab.Main;

import pl.coderslab.DAO.ExerciseDao;
import pl.coderslab.model.Exercise;

import java.util.Scanner;

public class MainExercise {
    private static final String WELCOME_VARIABLE  = " Wybierz jedną z opcji:\n" +
            "\n" +
            "    add – dodanie zadania,\n" +
            "    edit – edycja zadania,\n" +
            "    delete – usunięcia zadania,\n" +
            "    quit – zakończenie programu.";
    private static final String ADD = "add";
    private static final String EDIT = "edit";
    private static final String DELETE = "delete";
    private static final String QUIT = "quit";
    private static Exercise exercise = new Exercise();
    private static ExerciseDao exerciseDao = new ExerciseDao();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(QUIT)) {
            Exercise[] exercises = exerciseDao.findAll();
            iterateWrite(exercises);
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

    private static void iterateWrite(Exercise[] exercises){
        for (int i = 0; i < exercises.length; i++) {
            System.out.println(exercises[i]);
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

        System.out.printf("Podaj nową nazwę ćwiczenia:\n");
        String exerciseTitle = scanner.next();
        System.out.printf("Podaj nowy opis ćwiczenia:\n");
        String exerciseDescription = scanner.next();

        exercise.setTitle(exerciseTitle);
        exercise.setDescription(exerciseDescription);
        exerciseDao.create(exercise);
    }

    private static void edit(Scanner scanner){

        System.out.printf("Podaj ID ćwiczenia do zmiany:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        int exerciseId = scanner.nextInt();
        System.out.printf("Podaj nowy tytuł ćwiczenia:\n");
        String exerciseTitle = scanner.next();
        System.out.printf("Podaj opis ćwiczenia:\n");
        String exerciseDescription = scanner.next();

        exercise.setId(exerciseId);
        exercise.setTitle(exerciseTitle);
        exercise.setDescription(exerciseDescription);
        exerciseDao.update(exercise);
    }
    private static void delete(Scanner scanner){

        int input;
        System.out.println("Podaj ID ćwiczenia do usunięcia:\n");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:\n");
        }
        input = scanner.nextInt();
        exerciseDao.delete(input);
    }
}
