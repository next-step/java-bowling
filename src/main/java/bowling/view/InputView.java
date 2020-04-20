package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String NUMBER_QUESTION = "How many people? ";
    private static final String NAME_QUESTION = "플레이어 %d의 이름은(3 english letters)? : ";
    private static final String PIN_QUESTION = "%s's turn : ";
    private Scanner scanner;

    public int askNumberOfPeople() {
        System.out.print(NUMBER_QUESTION);
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String askName(int number) {
        System.out.printf(NAME_QUESTION, number + 1);
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int askNumberOfPin(String name){
        System.out.printf(PIN_QUESTION, name);
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
