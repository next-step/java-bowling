package bowling;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {

    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;
    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;
    private static final int STRIKE_COUNT = 10;

    private final List<Score> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public static FrameScore newInstance(final List<Score> scores) {

    }

    public void pitchFirst(final int score) {
        scores.add(Score.of(score));
    }

    public void pitchSecond(final int score) {
        Score firstScore = scores.get(0);
        scores.add(firstScore.secondPitching(score));
    }

    public void pitchThird(final int score) {
        scores.add(Score.of(score));
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isOver(final boolean isLastFrame) {
        if ((isLastFrame && isOverStatus(LAST_FRAME_FULL_PITCH_COUNT)) || isOverStatus(COMMON_FRAME_FULL_PITCH_COUNT)) {
            return true;
        }

        return false;
    }

    private boolean isOverStatus(final int fullScoreSize) {
        int canPitchScoreSize = fullScoreSize - 1;

        return (scores.size() == canPitchScoreSize && scores.get(canPitchScoreSize - 1).isEqualsTo(STRIKE_COUNT)) || scores.size() == fullScoreSize;
    }
}
