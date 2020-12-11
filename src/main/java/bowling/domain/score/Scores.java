package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Scores {
    static final int SECOND_SCORE = 2;
    static final int FIRST_SCORE = 1;

    protected List<Score> scores;
    private int bonusScoreCount;

    protected Scores() {
        scores = new ArrayList<>();
        bonusScoreCount = 0;
    }

    public abstract boolean canBowl();

    public abstract Score getScore(int bonusScoreCount);

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

        if(isStrike()) {
            bonusScoreCount = 2;
        }
        if(isSpare()) {
            bonusScoreCount = 1;
        }
    }

    public boolean isSpare() {
        if(scores.size() == SECOND_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .sum(scores.get(SECOND_SCORE - 1))
                    .isStrike();
        }
        return false;
    }

    public boolean isStrike() {
        if(scores.size() == FIRST_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .isStrike();
        }
        return false;
    }

    public List<Score> getResult() {
        return scores;
    }

    public int getBonusScoreCount() {
        return bonusScoreCount;
    }

    public boolean hasFirstScore() {
        return scores.size() >= FIRST_SCORE;
    }
    public Score getTotalScore() {
        return scores.stream()
                .reduce(Score.ZERO_SCORE, Score::sum);
    }
}
