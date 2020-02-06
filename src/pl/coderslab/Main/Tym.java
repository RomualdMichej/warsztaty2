package pl.coderslab.Main;

import pl.coderslab.DAO.ExerciseDao;
import pl.coderslab.DAO.SolutionDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tym {

    private static final String WELCOME_VARIABLE  = "Wybierz jedną z opcji: " +
            "\nadd – dodawanie rozwiązania, " +
            "\nview – przeglądanie swoich rozwiązań." +
            "\nquit – zakończenie programu.";
    private static final String A = "add";
    private static final String V = "view";
    private static final String Q = "quit";

    public static void main(String[] args) {

        int userId;

        List<Integer> tempList = new ArrayList<>();
        try {
           for (int i = 0; i < args.length; i++) {
               tempList.add(Integer.parseInt(args[i]));
           }
            if(tempList.size() > 1){
                System.out.println("\nPodaj tylko jedną liczbę całkowitą.");
            } else if(tempList.size() == 1) {
                userId = tempList.get(0);
                work(userId);
            }
        } catch (NumberFormatException e){
            System.out.println("\nNieprawidłowe dane! Podaj jedną liczbę całkowitą.");
        }
    }
    private static void work(int userId) {

        System.out.println("\nTwój numer yrzytkownika to: " + userId + "\n");

        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (!input.equals(Q)) {

            System.out.println(WELCOME_VARIABLE);
            input = check(scanner);
            if (input.equals(A)) {
                add(userId);
            } else if (input.equals(V)) {
                view(userId);
            }
        }
    }
    private static String check(Scanner scanner) {
        String input = "";
        while (!input.equals(A) && !input.equals(V) && !input.equals(Q)) {
            input = scanner.next();
            if (!input.equals(A) && !input.equals(V) && !input.equals(Q)) {
                System.out.println("\nNieprawidłowe dane. Podaj jeszcze raz:");
            }
        }
        return input;
    }

    private static void view(int userId){

        SolutionDao solutionDao = new SolutionDao();
        List<Solution> listSolutions = solutionDao.findAllByUserId(userId);

        System.out.println("\nLista Twoich rozwiązań do zadań:\n");
        for (int i = 0; i < listSolutions.size(); i++) {
            System.out.println(listSolutions.get(i));
        }
        System.out.println("\n");
    }

    private  static void add(int userId) {

        SolutionDao solutionDao = new SolutionDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Solution> listSolutions = solutionDao.findAllByUserId(userId);
        List<Integer> listOfExId = new ArrayList<>();
        List<Integer> listOfExId1 = new ArrayList<>();
        List<Integer> listOfAllExId = new ArrayList<>();
        Exercise[] exercises = exerciseDao.findAll();
        Scanner scanner = new Scanner(System.in);

        for (Solution listSolution : listSolutions) {
            listOfExId.add(listSolution.getExercise_id());
        }

        List<Exercise> listOfExecise = new ArrayList(Arrays.asList(exercises));

        for (Exercise exercise : listOfExecise) {
            listOfExId1.add(exercise.getId());
            listOfAllExId.add(exercise.getId());
        }

        for (Integer integer : listOfExId) {
            for (int j = 0; j < listOfExId1.size(); j++) {
                if (listOfExId1.get(j).equals(integer)) {
                    listOfExId1.remove(j);
                }
            }
        }
        System.out.println("\nLista zadani dla których nie wprowadziłeś jeszcze rozwiązania:\n");
        for (Integer integer : listOfExId1) {
            System.out.println(exerciseDao.read(integer));
        }

        System.out.println("\nPodaj ID zadania do którego ma zostać dodane rozwiązanie.\n");
        intCheck(scanner);
        int exerciseId = scanner.nextInt();

        while(!listOfExId1.contains(exerciseId)){
            if(!listOfAllExId.contains(exerciseId)){
                System.out.println("\nNie ma zadania o takim id. Podaj jeszcze raz:\n");
                intCheck(scanner);
                exerciseId = scanner.nextInt();
            }else {
                System.out.println("\nRozwóązanie do tego zadania już istnieje. Podaj jeszcze raz:\n");
                intCheck(scanner);
                exerciseId = scanner.nextInt();
            }
        }

        System.out.println("\nPodaj opis rozwiązania.\n");
        String description = scanner.next();

        Solution solution = new Solution(description, exerciseId, userId);
        solutionDao.create(solution);
    }

    private static void intCheck(Scanner scanner){

        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("\nNieprawidłowe dane. Podaj jeszcze raz:\n");
        }
    }
}
