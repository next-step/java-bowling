package view;

import domain.Pins;
import domain.frame.FrameIndex;

import java.util.Scanner;

public class InputView {

    private static final String MESSAGE_FOR_ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static Scanner scanner = new Scanner(System.in);

    public static String askPlayerName() {
        System.out.print(MESSAGE_FOR_ASK_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static Pins askFallenPins(FrameIndex index) {
        System.out.print(String.format("%s프레임 투구 : ", index.getFrameIndex()));
        int fallenPins = scanner.nextInt();
        scanner.nextLine();
        return Pins.from(fallenPins);
    }
}
