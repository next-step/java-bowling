package bowling;

import bowling.domain.FallingPinCount;
import bowling.domain.game.BowlingGame;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class RunGame {

    public static void main(String[] args) {
        BowlingGame game = new BowlingGame(InputView.getPlayer());
        OutputView.showInitializedGame(game);
        while (!game.isGameFinished()) {
            FallingPinCount currentFramePitch = InputView.getCurrentFramePitch(game.getCurrentFrameSequence());
            game.play(currentFramePitch);
            OutputView.showDashBoard(game);
        }
    }
}
