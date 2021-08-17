package bowling.domain.frame;


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

    public static PitchResult of(final String number) {
        try {
            return new PitchResult(Integer.parseInt(number));
        } catch (final Exception ex) {
            throw new PitchResultCreateException();
        }
    }

    public static PitchResult zero() {
        return new PitchResult(0);
    }

    public int add(final PitchResult pitchResult) {
        return this.number + pitchResult.number;
    }

    public boolean isStrike() {
        return number == MAX;
    }

    public int getNumber() {
        return number;
    }
}
