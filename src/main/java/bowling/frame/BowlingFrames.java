package bowling.frame;

import bowling.Pin;
import bowling.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingFrames {
    public static final int MAX_BOWLING_FRAME_SIZE = 10;

    private final List<BowlingFrame> frames;

    private BowlingFrames(final List<BowlingFrame> frames) {
        this.frames = new ArrayList<>(frames);
    }

    public static BowlingFrames newInstance() {
        BowlingFrame firstFrame = BowlingFrame.createFirstFrame();
        return new BowlingFrames(Collections.singletonList(firstFrame));
    }

    public void bowl(final Pin pinCount) {
        BowlingFrame recentFrame = getRecentBowlingFrame();
        recentFrame.bowl(pinCount);
    }

    private void prepareNextFrame(final BowlingFrame recentFrame) {
        if (recentFrame.isOver() && frames.size() < MAX_BOWLING_FRAME_SIZE) {
            frames.add(recentFrame.appendNextFrame(frames.size()));
        }
    }

    public boolean isAllFrameOver() {
        if (isRecentFrameOver()) {
            return frames.size() == MAX_BOWLING_FRAME_SIZE;
        }

        return false;
    }

    public boolean isRecentFrameOver() {
        return getRecentBowlingFrame().isOver();
    }

    private BowlingFrame getRecentBowlingFrame() {
        return frames.get(frames.size() - 1);
    }

    public int size() {
        return frames.size();
    }

    public List<Score> getTotalScores() {
        List<Score> totalScores = new ArrayList<>();
        Score totalScore = Score.ofZeroPins();

        for (BowlingFrame bowlingFrame : frames) {
            Score subTotalScore = bowlingFrame.getTotalScore(totalScore);
            totalScore = subTotalScore;
            totalScores.add(subTotalScore);
        }

        return totalScores;
    }

    public BowlingFrame getFrame(final int index) {
        return frames.get(index);
    }

    public List<BowlingFrame> getFrames() {
        return frames;
    }
}
