package view;

import domain.Pins;
import domain.frame.FrameIndex;

import java.util.Scanner;

public class InputView {

    private static final String MESSAGE_FOR_ASK_NUMBER_OF_PLAYERS = "How many people? ";
    private static Scanner scanner = new Scanner(System.in);

    public static int askNumberOfPlayers() {
        System.out.print(MESSAGE_FOR_ASK_NUMBER_OF_PLAYERS);
        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        return numberOfPlayers;
    }

    public static String askPlayerName(int index) {
        System.out.print(String.format("플레이어 %d의 이름은?(3 english letters): ", index));
        return scanner.nextLine();
    }

    public static Pins askFallenPins(FrameIndex index) {
        System.out.print(String.format("%s프레임 투구 : ", index.getFrameIndex()));
        int fallenPins = scanner.nextInt();
        scanner.nextLine();
        return Pins.from(fallenPins);
    }
}
