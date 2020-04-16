package bowling;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    private static final int ONE = 1;

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayer());
        ResultView.printBowlingFrame(player);

        Frames frames = Frames.of();
        while (!frames.isOver()) {
            frames.addNextFrame();
            playBowling(frames, player);
        }
    }

    private static void playBowling(Frames frames, Player player) {
        Frame frame = frames.getLast();
        while (frame.isPlayable()) {
            //BowlingGame.play(frame, InputView.relaseBowling(frames.size() - ONE));
            frame.addScore(InputView.relaseBowling(frames.size() - ONE));
            ResultView.printBowlingScore(frames, player);
        }
    }
}
