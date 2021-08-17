package bowling.domain.frame;


import bowling.domain.frame.exception.PitchResultAddException;
import bowling.domain.frame.exception.PitchResultCreateException;

public class PitchResult {

    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int number;

    private PitchResult(final int number) {
        if (number < MIN || number > MAX) {
            throw new PitchResultCreateException();
        }

        this.number = number;
    }

    public static PitchResult of(final int number) {
        return new PitchResult(number);
    }

    public int add(final PitchResult pitchResult) {
        if (this.number + pitchResult.number > MAX) {
            throw new PitchResultAddException();
        }

        return this.number + pitchResult.number;
    }
}
