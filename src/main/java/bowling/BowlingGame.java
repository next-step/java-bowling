package bowling;

import bowling.domain.Player;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingGame {
    private static final int FRAME_INDEX_NINE = 9;
    private static final int ZERO = 0;
    private static final int ONE = 1;

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
