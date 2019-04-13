package bowling.console.view;

import bowling.game.BowlingGame;

public class ConsoleOutputView {
    private ConsoleOutputView() {
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printBowlingGame(BowlingGame bowlingGame) {
        System.out.println(bowlingGame);
    }
}
