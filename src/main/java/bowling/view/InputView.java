package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_USERNAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputUsername() {
        System.out.print(INPUT_USERNAME_MESSAGE);
        return scanner.nextLine();
    }

}
