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

    public static FrameScore newInstance(final List<Integer> scoreNumbers) {
        FrameScore frameScore = new FrameScore();
        scoreNumbers.forEach(frameScore::add);

        return frameScore;
    }

    public void add(final int scoreCount) {
        Score score = Score.of(scoreCount);
        if (scores.size() >= 1) {
            score = nextScore(scoreCount);
        }

        scores.add(score);
    }

    private Score nextScore(final int scoreCount) {
        Score lastScore = scores.get(scores.size() - 1);
        return lastScore.next(scoreCount);
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isOverLastFrame() {
        if (scores.size() == LAST_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == LAST_FRAME_FULL_PITCH_COUNT - 1 && !canThrowThirdPitching();
    }

    public boolean isOverCommonFrame() {
        if (scores.size() == COMMON_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == COMMON_FRAME_FULL_PITCH_COUNT - 1 && isStrikeAtFirstScore();
    }

    private boolean canThrowThirdPitching() {
        return scores.stream()
                .anyMatch(Score::isStrike) || isSpareForTwoScores();
    }

    private boolean isStrikeAtFirstScore() {
        return scores.size() == 1 && sum() == 10;
    }

    private boolean isSpareForTwoScores() {
        return scores.size() == 2 && sum() == 10;
    }
}
