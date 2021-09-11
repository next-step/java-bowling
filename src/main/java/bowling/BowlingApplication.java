package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Scanner;

public class BowlingApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);

        String playerName = inputView.getInputPlayerName();
        BowlingGame bowlingGame = new BowlingGame(playerName);

        OutputView.scoreBoard(bowlingGame);
        while (!bowlingGame.isGameOver()) {
            int knockDownNumber = inputView.getInputScore(bowlingGame.nextRoundNumber());
            bowlingGame.input(knockDownNumber);
            OutputView.scoreBoard(bowlingGame);
        }
    }
}