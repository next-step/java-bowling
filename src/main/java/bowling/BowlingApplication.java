package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        List<String> playerNames = InputView.inputPlayers();

        BowlingGame bowlingGame = BowlingGame.of(playerNames);
        ResultView.printHeader(bowlingGame.getGameSets());

        while (!bowlingGame.isGameOver()) {
            play(bowlingGame);
            ResultView.printShape(bowlingGame.getGameSets());

            bowlingGame.turnOverGameSet();
        }
    }

    private static void play(final BowlingGame bowlingGame) {
        int hitCount = InputView.inputHitCount(bowlingGame.getPlayerName());

        bowlingGame.playOfCurrentGameSet(hitCount);
    }
}
