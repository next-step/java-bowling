package bowling.score;

import java.util.List;

public abstract class Scores {
    protected Score firstScore;
    protected Score secondScore;

    protected Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static boolean isSpare(List<Score> scores) {
        if (scores.get(0) == null || scores.get(1) == null) {
            return false;
        }

        return Score.of(scores.get(0).toString())
                .sum(Score.of(scores.get(1).toString()))
                .isStrike();
    }

    protected boolean hasFirstScore() {
        return firstScore != null;
    }

    protected boolean hasSecondScore() {
        return secondScore != null;
    }

    public abstract void add(Score score);

    public abstract boolean canBowl();

    public abstract List<Score> getResult();
}
