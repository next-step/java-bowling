package step2.domain.state;

import step2.domain.Pitch;
import step2.domain.Score;

public class Spare extends Finished {

    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private Pitch firstPitch;
    private Pitch secondPitch;


    public Spare(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public Score getScore() {
        return Score.of(Pitch.MAX_SCORE, 1);
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
