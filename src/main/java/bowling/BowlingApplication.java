package bowling;


import bowling.domian.frame.Frames;
import bowling.domian.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        Player player = InputView.getPlayer();
        Frames frames = Frames.of();

        ResultView.printFrames(player, frames.getBoard());
        while(!frames.isGameEnd()) {
            int falledPinsCount = InputView.getFalledPinsCount(frames.getCurrentFrameNumber());
            frames.bowl(falledPinsCount);
            ResultView.printFrames(player, frames.getBoard());
        }
    }
}
