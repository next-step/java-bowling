package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        Player player = InputView.getPlayer();
        Frames frames = Frames.init();

        ResultView.printFrame(player, frames);
        while (frames.canPlay()) {
            int knockedDownPinCount = InputView.getRollingCount(frames.getFrameNumber());
            frames.play(knockedDownPinCount);
            ResultView.printFrame(player, frames);
        }
    }
}
