package bowling;

import bowling.domain.Player;
import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.LastFrame;

public class BowlingGame {
    private static final int FRAME_INDEX_NINE = 9;
    private static final int ZERO = 0;

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

        int lastFrameIndex = frames.size() - 1;
        if (isLast(frames)) {
            frames.add(((DefaultFrame) frames.getLast()).nextFrame(lastFrameIndex));
            return;
        }
        frames.add(((DefaultFrame) frames.getLast()).lastFrame(lastFrameIndex));
    }

    private static boolean isLast(Frames frames) {
        return frames.size() != FRAME_INDEX_NINE;
    }

    private static boolean isFrist(Frames frames) {
        return frames.size() == ZERO;
    }
}
