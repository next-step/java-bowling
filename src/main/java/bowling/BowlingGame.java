package bowling;

import bowling.domain.Player;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingGame {
    public static void play(Frame defaultFrame, int score) {
        defaultFrame.addScore(score);
    }

    public static Frames startGame(Player player) {
        return Frames.of(player);
    }

    public static void nexrFrame(Frames frames) {
        frames.nextFrame();
    }
}
