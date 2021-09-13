package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        int numberOfPlayer = InputView.InputNumberOfPlayer();

        String[] names = new String[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++) {
            names[i] = InputView.inputPlayerName(i + 1);
        }

        BowlingGames bowlingGames = new BowlingGames(names);

        printBowlingGame(bowlingGames);

        while (bowlingGames.isNotEnd()) {
            for (BowlingGame game : bowlingGames.games()) {
                roll(game, bowlingGames);
            }
        }

    }

    private static void roll(BowlingGame game, BowlingGames bowlingGames) {
        int fallenPin = InputView.nextFallenPin(game);
        game.roll(fallenPin);
        printBowlingGame(bowlingGames);
        if (!game.currentFrameIsEnd()) {
            roll(game, bowlingGames);
        }
    }

    private static void printBowlingGame(BowlingGames bowlingGames) {
        ResultView.printFrame();
        bowlingGames.games().forEach(game -> {
            ResultView.printFrameByPlayer(game);
            ResultView.printScoreByPlayer(game);
        });
    }

}
