package bowling.view;

import bowling.domain.ScoreStatus;
import lombok.Getter;

public class ScoreView {
    private static final String BLANK = "";

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

    public static ScoreView of(ScoreStatus status, String sign) {
        if (status.endCalculate()) {
            return new ScoreView(status.getTotalScore(), sign);
        }

        return new ScoreView(BLANK, sign);
    }

    public static ScoreView of(String score, String sign) {
        return new ScoreView(score, sign);
    }
}
