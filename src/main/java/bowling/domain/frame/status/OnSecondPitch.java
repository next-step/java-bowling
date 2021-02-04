package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidPinStatusException;
import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;

public class OnSecondPitch extends OnPitching {

    private final DownedPin firstPitch;

    public OnSecondPitch(int firstPitch) {
        DownedPin pitch = DownedPin.fromNumber(firstPitch);

        if (pitch.isStrike()) {
            throw new InvalidPinStatusException();
        }

        this.firstPitch = pitch;
    }

    @Override
    public String getDescription() {
        return firstPitch.getDescriptionForm();
    }

    @Override
    public Status record(int downedPin) {
        DownedPin additionalPitch = firstPitch.fromPreviousPitch(downedPin);

        if (firstPitch.isSpare(additionalPitch)) {
            return new Spare(firstPitch, additionalPitch);
        }

        return new Miss(firstPitch, additionalPitch);
    }

    @Override
    public Score addBonus(Score originalScore) {
        return firstPitch.addToScore(originalScore);
    }
}
