package bowling.step3.domain;

import bowling.step3.domain.scores.NormalScores;

import java.util.List;

public enum ScoreType {
    STRIKE("X"),
    SPARED("/"),
    GUTTER("-");

    private final String value;

    ScoreType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String toScoreTypeValue(List<Score> scores, int index) {
        if (scores.get(index).isType(ScoreType.GUTTER)) {
            return ScoreType.GUTTER.getValue();
        }
        if (scores.get(index).isType(ScoreType.STRIKE)) {
            return ScoreType.STRIKE.getValue();
        }
        if (index == 1 && NormalScores.isType(scores, ScoreType.SPARED)) {
            return ScoreType.SPARED.getValue();
        }
        return scores.get(index).toString();
    }
}
