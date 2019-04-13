package bowling.console.view;

import bowling.game.BowlingGame;

import java.util.Scanner;

public class ConsoleInputView {
    private ConsoleInputView() {
    }

    public static String inputPlayerName(Scanner scanner) {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine().trim();
    }

    public static int inputPinScore(Scanner scanner, BowlingGame bowlingGame) {
        System.out.print(bowlingGame.getCurrentFrameOrder() + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
