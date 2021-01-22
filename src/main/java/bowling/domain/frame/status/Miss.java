package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidPinStatusException;
import bowling.bowlingexception.InvalidScoreCalculationException;
import bowling.domain.frame.DownedPin;

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
    public int calculateAccumulatedScore() {
        throw new InvalidScoreCalculationException();
    }
}
