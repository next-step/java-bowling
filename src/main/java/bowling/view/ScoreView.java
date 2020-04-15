package bowling.view;

import lombok.Getter;

public class ScoreView {
    @Getter
    private String score;

    @Getter
    private String sign;

    private ScoreView(int score, String sign) {
        this.score = String.valueOf(score);
        this.sign = sign;
    }

    public ScoreView(String score, String sign) {
        this.score = score;
        this.sign = sign;
    }

    public static ScoreView of(int score, String sign) {
        return new ScoreView(score, sign);
    }

    public static ScoreView of(String score, String sign) {
        return new ScoreView(score, sign);
    }
}
