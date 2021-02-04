package bowling.domain.frame.status;

import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;

public class Strike extends Ended {

    private final DownedPin firstPitch;

    public Strike() {
        firstPitch = DownedPin.fromNumber(10);
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm();
    }

    @Override
    public Score calculateBaseScoreOfFrame() {
        return new Score(10, 2);
    }

    @Override
    public Score addBonus(Score originalScore) {
        return firstPitch.addToScore(originalScore);
    }
}
