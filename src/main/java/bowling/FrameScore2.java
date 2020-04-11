package bowling;

import java.util.ArrayList;
import java.util.List;

public class FrameScore2 {

    private final List<Score> scores;

    public FrameScore2() {
        this.scores = new ArrayList<>();
    }

    public static FrameScore2 newInstance(final List<Integer> scores) {
        FrameScore2 frameScore = new FrameScore2();
        scores.forEach(frameScore::pitch);

        return frameScore;
    }

    public void pitch(int scoreCount) {
        Score score = Score.of(scoreCount);

        if (scores.size() == 1) {
            score = secondPitchingScore(scoreCount);
        }

        scores.add(score);
    }

    public int sum() {
        return Score.sum(scores);
    }

    private Score secondPitchingScore(final int scoreCount) {
        Score firstScore = scores.get(0);
        return firstScore.secondPitching(scoreCount);
    }

    public boolean isOver(final int fullPitchCount, final boolean extraOverCondition) {
        if (scores.size() == fullPitchCount) {
            return true;
        }

        return scores.size() == fullPitchCount - 1 && extraOverCondition;
    }

    public boolean hasStrikeOrSpare() {
        return scores.stream()
                .anyMatch(Score::isStrike) || isSpare();
    }

    public boolean isStrike() {
        return scores.size() == 1 && sum() == 10;
    }

    public boolean isSpare() {
        return scores.size() == 2 && sum() == 10;
    }
}
