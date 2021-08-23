package bowling.domain;

import bowling.domain.exception.PitchResultCreateException;

public class Pitch {

    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int pinCount;
    private final PitchResult pitchResult;

    public Pitch(final int pinCount, final PitchResult pitchResult) {
        createValidation(pinCount);

        this.pinCount = pinCount;
        this.pitchResult = pitchResult;
    }

    public Pitch(final int pinCount) {
        createValidation(pinCount);

        this.pinCount = pinCount;
        this.pitchResult = PitchResult.findByPinCount(pinCount);
    }

    private void createValidation(final int pinCount) {
        if (pinCount < MIN || pinCount > MAX) {
            throw new PitchResultCreateException();
        }
    }

    public Pitch next(final int pinCount) {
        return new Pitch(pinCount, PitchResult.findByPinCount(this.pinCount, pinCount));
    }

    public int getPinCount() {
        return pinCount;
    }

    public PitchResult getPitchResult() {
        return pitchResult;
    }
}
