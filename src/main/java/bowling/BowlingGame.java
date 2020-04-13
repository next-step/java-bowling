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
        if (isFrist(frames)) {
            frames.add(DefaultFrame.first());
            return;
        }

        int currentIndex = frames.size() - ONE;
        if (isLast(frames)) {
            frames.add(((DefaultFrame) frames.getLast()).nextFrame(currentIndex));
            return;
        }
        frames.add(((DefaultFrame) frames.getLast()).lastFrame(currentIndex));
    }

    private static boolean isLast(Frames frames) {
        return frames.size() != FRAME_INDEX_NINE;
    }

    private static boolean isFrist(Frames frames) {
        return frames.size() == ZERO;
    }
}
