package view;

import java.util.Scanner;

public class InputView {

    private static final String MESSAGE_FOR_ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static Scanner scanner = new Scanner(System.in);

    public static String askPlayerName() {
        System.out.print(MESSAGE_FOR_ASK_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int askFallenPins(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
        int fallenPins = scanner.nextInt();
        scanner.nextLine();
        return fallenPins;
    }
}
