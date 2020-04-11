package bowling;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {

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
        Score firstScore = scores.get(scores.size() - 1);
        return firstScore.next(scoreCount);
    }

    public int sum() {
        return Score.sum(scores);
    }

    public boolean isOver(final int fullPitchCount, final boolean extraOverCondition) {
        if (scores.size() == fullPitchCount) {
            return true;
        }

        return scores.size() == fullPitchCount - 1 && extraOverCondition;
    }

    public boolean isStrikeOrSpare() {
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
