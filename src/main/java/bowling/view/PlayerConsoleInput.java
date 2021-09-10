package bowling.view;

import java.util.Scanner;

public class PlayerConsoleInput {

    private static final String NAME_QUESTION = "\n플레이어 이름은(3 english letters)?: ";
    private static final Scanner scanner = new Scanner(System.in);

    public static String askName() {
        System.out.print(NAME_QUESTION);
        return scanner.nextLine();
    }

}
