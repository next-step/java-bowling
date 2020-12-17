package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Point;
import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class RunGame {

    public static void main(String[] args) {
        Player player = InputView.getPlayer();
        OutputView.showInitializedGame(player);
        Frames frames = Frames.create();

        while (!frames.isLast()) {
            Point point = InputView.getCurrentFramePitch(frames.getFrameIndex());
            frames = frames.pitch(point);

            OutputView.showDashBoard(player, frames);
            frames.next();
        }
    }
}
