package step4.view;

import java.util.Scanner;

public class InputView {
    private static Scanner SC = new Scanner(System.in);
    private static String NUM_OF_PEOPLE = "How many people? ";
    private static String NAME_OF_PERSON = "플레이어 1의 이름은?(3 english letters): ";
    private static String THROW_BOWL = "%s's turn: ";

    public static int numOfPeople() {
        System.out.print(NUM_OF_PEOPLE);
        return Integer.parseInt(SC.nextLine());
    }

    public static String nameOfPerson() {
        System.out.print(NAME_OF_PERSON);
        return SC.nextLine();
    }

    public static int throwBowl(String personName) {
        System.out.printf(THROW_BOWL, personName);
        return Integer.parseInt(SC.nextLine());
    }
}
