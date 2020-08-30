package bowling;

import bowling.domian.frame.Frames;
import bowling.domian.player.Player;
import bowling.domian.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        Players players = InputView.getPlayers();
        Frames frames = Frames.of();

        ResultView.printFrames(players);
        while(!frames.isGameEnd()) {
            int falledPinsCount = InputView.getFalledPinsCount(frames.getCurrentFrameNumber());
            frames.bowl(falledPinsCount);
            ResultView.printFrames(players);
        }
    }
}
