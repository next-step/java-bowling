package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidPinStatusException;
import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;

public class Miss extends Ended {
    private final DownedPin firstPitch;
    private final DownedPin secondPitch;

    public Miss(DownedPin firstPitch, DownedPin secondPitch) {
        if (firstPitch.isSpare(secondPitch)) {
            throw new InvalidPinStatusException();
        }

        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm(secondPitch);
    }

    @Override
    public Score calculateOriginalScoreOfFrame() {
        return new Score(firstPitch.calculateSum(secondPitch), 0);
    }
}
