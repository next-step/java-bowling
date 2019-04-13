package bowling.console;

import bowling.console.view.ConsoleInputView;
import bowling.console.view.ConsoleOutputView;
import bowling.game.BowlingGame;
import bowling.game.PinScore;
import bowling.player.Player;

import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = ConsoleInputView.inputPlayerName(scanner);
            BowlingGame bowlingGame = new BowlingGame(new Player(name));

            while (!bowlingGame.isEnd()) {
                int pinScore = ConsoleInputView.inputPinScore(scanner, bowlingGame);
                bowlingGame.pitch(PinScore.of(pinScore));

                ConsoleOutputView.printBowlingGame(bowlingGame);
                ConsoleOutputView.printEmptyLine();
            }
        }

    }
}
