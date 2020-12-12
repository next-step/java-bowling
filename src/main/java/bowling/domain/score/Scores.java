package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Scores {
    static final int SECOND_SCORE = 2;
    static final int FIRST_SCORE = 1;

    protected List<Score> scores;

    protected Scores() {
        scores = new ArrayList<>();
    }

    public abstract boolean canBowl();

    public void add(Score score) {
        scores.add(score);
    }

    public static boolean isSpare(List<Score> scores) {
        if (scores.get(0) == null || scores.get(1) == null) {
            return false;
        }

        return Score.of(scores.get(0).toString())
                .sum(Score.of(scores.get(1).toString()))
                .isStrike();
    }

    public boolean isSpare() {
        if (scores.size() == SECOND_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .sum(scores.get(SECOND_SCORE - 1))
                    .isStrike();
        }
        return false;
    }

    public boolean isStrike() {
        if (scores.size() == FIRST_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .isStrike();
        }
        return false;
    }

    public boolean hasFirstScore() {
        return scores.size() >= FIRST_SCORE;
    }

    public List<Score> getResult() {
        return scores;
    }

    public Score getTotalScore() {
        return scores.stream()
                .reduce(Score.ZERO_SCORE, Score::sum);
    }

    public Score getScore(int bonusScoreCount) {
        int addCount = Math.min(scores.size(), bonusScoreCount);

        return scores.stream()
                .limit(addCount)
                .reduce(Score.ZERO_SCORE, Score::sum);
    }
}
