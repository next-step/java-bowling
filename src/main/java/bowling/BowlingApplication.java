package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class BowlingApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);

        int playerCount = inputView.getInputPlayerCount();
        List<String> playerNames = inputView.getInputPlayerNames(playerCount);

        BowlingGame bowlingGame = new BowlingGame(playerNames);
        OutputView.scoreBoard(bowlingGame);
        while (!bowlingGame.isGameOver()) {
            String currentTurnPlayer = bowlingGame.getCurrentTurn();
            int knockDownNumber = inputView.getInputScore(currentTurnPlayer);
            bowlingGame.input(knockDownNumber);
            OutputView.scoreBoard(bowlingGame);
        }
    }
}