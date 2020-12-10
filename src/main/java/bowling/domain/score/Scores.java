package bowling.domain.score;

import java.util.List;

public abstract class Scores {
    static final int SECOND_SCORE = 2;
    static final int FIRST_SCORE = 1;

    protected List<Score> scores;

    protected Scores(Score firstScore, Score secondScore) {
        scores.add(firstScore);
        scores.add(secondScore);
    }

    public static boolean isSpare(List<Score> scores) {
        if (scores.get(0) == null || scores.get(1) == null) {
            return false;
        }

        return Score.of(scores.get(0).toString())
                .sum(Score.of(scores.get(1).toString()))
                .isStrike();
    }

    public void add(Score score) {
        scores.add(score);
    }

    public List<Score> getResult() {
        return scores;
    }

    public abstract boolean canBowl();

}
