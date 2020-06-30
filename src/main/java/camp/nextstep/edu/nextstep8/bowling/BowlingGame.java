package camp.nextstep.edu.nextstep8.bowling;

import java.util.HashMap;
import java.util.Map;

public class BowlingGame {
    private static final int LAST_FRAME = 11;

    private int frameIndex = 1;
    private final Map<Integer, Frame> frames = new HashMap<>();

    public void roll() {
        markScore(BowlingGameInput.getHitCount(frameIndex));
        if(hasSpareChance()) {
            markSpare(BowlingGameInput.getHitCount(frameIndex));
        }
        frameIndex++;
    }

    private void markScore(int score) {
        frames.put(frameIndex, new Frame(score));
    }

    private void markSpare(int spare) {
        frames.get(frameIndex)
                .updateSpare(spare);
    }

    private boolean hasSpareChance() {
        return frames.get(frameIndex).hasSpareChance();
    }

    public boolean hasNextFrame() {
        return LAST_FRAME > this.frameIndex;
    }

    public boolean hasLastChance() {
        int lastFrameIndex = frameIndex - 1;
        return LAST_FRAME == this.frameIndex &&
                frames.get(lastFrameIndex)
                        .isStrikeOrSpare();
    }

    public ScoreBoard getScoreBoard() {
        return new ScoreBoard(frames);
    }
}
