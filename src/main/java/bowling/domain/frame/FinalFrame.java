package bowling.domain.frame;

import java.util.Arrays;
import java.util.List;

public class FinalFrame extends Frame {

    public FinalFrame(List<Score> scores) {
        this.scores = scores;
    }

    public static FinalFrame valueOf(int score) {
        return new FinalFrame(Arrays.asList(Score.valueOf(score)));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void addScore(int score) {

    }
}
