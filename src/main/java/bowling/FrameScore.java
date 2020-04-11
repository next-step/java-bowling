package bowling;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {

    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;
    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;

    private final List<Score> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public static FrameScore newInstance(final List<Integer> scores, final boolean isLastFrame) {
        FrameScore frameScore = new FrameScore();
        scores.forEach(score -> frameScore.pitch(score, isLastFrame));

        return frameScore;
    }

    public void pitch(final int scoreCount, final boolean isLastFrame) {
        Score score = Score.of(scoreCount);
        if (scores.size() == 1) {
            score = secondPitchingScore(scoreCount, isLastFrame);
        }

        scores.add(score);
    }

    private Score secondPitchingScore(final int scoreCount, final boolean isLastFrame) {
        Score firstScore = scores.get(0);
        return firstScore.secondPitching(scoreCount, isLastFrame);
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isOver(final boolean isLastFrame) {
        if (isLastFrame) {
            return isOverWhenLastFrame();
        }
        return isOverWhenCommonFrame();
    }

    private boolean isOverWhenLastFrame() {
        if (scores.size() == LAST_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == LAST_FRAME_FULL_PITCH_COUNT - 1 && Score.sum(scores) % 10 != 0;
    }

    private boolean isOverWhenCommonFrame() {
        if (scores.size() == COMMON_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == COMMON_FRAME_FULL_PITCH_COUNT - 1 && Score.sum(scores) == 10;
    }

}
