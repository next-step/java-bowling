package seul.bowling.view;

import lombok.Getter;

public class ScoreView {
    private static final String BLANK = "";

    @Getter
    private String score;

    @Getter
    private String sign;

    public ScoreView(String score, String sign) {
        this.score = score;
        this.sign = sign;
    }

    public static ScoreView of(String score, String sign) {
        return new ScoreView(score, sign);
    }

    public static ScoreView of(boolean endScore, int score, String sign) {
        if (!endScore) {
            return ScoreView.of(BLANK, sign);
        }

        return ScoreView.of(String.valueOf(score), sign);
    }
}
