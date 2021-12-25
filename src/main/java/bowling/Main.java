package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String names = InputView.readPlayerName();

        BowlingGames bowlingGames = new BowlingGames(Arrays.asList(names));
        OutputView.printBowlingBoard(bowlingGames);

        while (!bowlingGames.isEnd()) {
            playBowlingGames(bowlingGames);
        }
    }

    private static void playBowlingGames(BowlingGames bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames.values()) {
            playBowlingGameAndPrintScore(bowlingGames, bowlingGame);
        }
    }

    private static void playBowlingGameAndPrintScore(BowlingGames bowlingGames, BowlingGame bowlingGame) {
        bowlingGame.prepareFrame();
        while (!bowlingGame.isCurrentFrameEnd()) {
            int knockedOutCount = InputView.readKnockedOutCountOf(bowlingGame);
            bowlingGame.bowl(knockedOutCount);
            OutputView.printBowlingBoard(bowlingGames);
        }
    }
}
