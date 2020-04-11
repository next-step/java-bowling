package bowling;

import java.util.ArrayList;
import java.util.List;

import static bowling.Score.STRIKE_COUNT;

public class FrameScore {

    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;
    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;

    private final List<Score> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public static FrameScore newInstance(final List<Integer> scores) {
        FrameScore frameScore = new FrameScore();
        scores.forEach(frameScore::pitch);

        return frameScore;
    }

    public void pitch(final int score, final boolean isLastFrame) {
        if (!isOver(isLastFrame)) {
            pitch(score);
        }
    }

    private void pitch(final int scoreCount) {
        Score score = Score.of(scoreCount);
        if (scores.size() == 1) {
            score = secondPitchingScore(scores.get(0), scoreCount);
        }

        scores.add(score);
    }

    private Score secondPitchingScore(final Score firstScore, final int scoreCount) {
        return firstScore.secondPitching(scoreCount);
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isOver(final boolean isLastFrame) {
        return (isLastFrame && isOverStatus(LAST_FRAME_FULL_PITCH_COUNT)) || isOverStatus(COMMON_FRAME_FULL_PITCH_COUNT);
    }

    private boolean isOverStatus(final int fullScoreSize) {
        int canPitchScoreSize = fullScoreSize - 1;

        return (scores.size() == canPitchScoreSize && scores.get(canPitchScoreSize - 1).isEqualsTo(STRIKE_COUNT)) || scores.size() == fullScoreSize;
    }
}
