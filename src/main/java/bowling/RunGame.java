package bowling;

import bowling.domain.game.BowlingGame;
import bowling.domain.game.BowlingGames;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class RunGame {

    public static void main(String[] args) {
        BowlingGames bowlingGames = InputView.prepareGames();
        OutputView.showInitializedGame(bowlingGames);

        while (!bowlingGames.isAllGameFinished()) {
            bowlingGames.getGames()
                .forEach(bowlingGame -> playEachGame(bowlingGame, bowlingGames));
        }
    }

    private static void playEachGame(BowlingGame bowlingGame, BowlingGames bowlingGames) {
        boolean frameIsDone = false;
        while (!frameIsDone && !bowlingGame.isFinished()) {
            int downPin = InputView.getCurrentFramePitch(bowlingGame.getPlayer());
            frameIsDone = bowlingGame.roll(downPin);
            OutputView.showDashBoard(bowlingGames);
        }
    }
}
