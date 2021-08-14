package bowling.domain.dto;

import bowling.domain.score.Score;

public class ScoreData {
    private final int score;

    private ScoreData(int score) {
        this.score = score;
    }

    public static ScoreData of(Score score) {
        return new ScoreData(score.getValue());
    }

    public int getValue() {
        return score;
    }
}
