package bowling;

import bowling.domain.game.BowlingGame;
import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class RunGame {

    public static void main(String[] args) {
        Player player = InputView.getPlayer();
        OutputView.showInitializedGame(player);

        BowlingGame bowlingGame = BowlingGame.start();

        while (!bowlingGame.isFinished()) {
            int frameNumber = bowlingGame.getFrameNumber();
            int downPin = InputView.getCurrentFramePitch(frameNumber);

            bowlingGame.roll(downPin);
            OutputView.showDashBoard(player, bowlingGame.getResult());
        }
    }
}
