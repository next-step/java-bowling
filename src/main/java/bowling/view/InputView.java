package bowling.view;


import java.util.Scanner;

public class InputView {
    private static final String MESSAGE_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner sc = new Scanner(System.in);

    public static String requirePlayerName() {
        System.out.println(MESSAGE_PLAYER_NAME);
        return sc.nextLine();
    }
}
