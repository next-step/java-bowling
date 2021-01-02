package step2.domain.state;

import step2.domain.Score;
import step2.domain.frame.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Miss extends Finished {

    private static final String DELIMITER = "|";

    private Pitch firstPitch;
    private Pitch secondPitch;

    public Miss(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public Score getScore() {
        int frameScore = firstPitch.getScore() + secondPitch.getScore();
        return Score.of(frameScore, 0);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = score.bowl(firstPitch.getScore());
        if (score.canCalculateScore()) {
            return score;
        }

        return score.bowl(secondPitch.getScore());
    }

    @Override
    public String getDesc() {
        return firstPitch.getDesc(secondPitch);
    }
}
