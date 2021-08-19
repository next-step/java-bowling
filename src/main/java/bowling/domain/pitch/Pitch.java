package bowling.domain.pitch;


import bowling.domain.pitch.exception.PitchResultCreateException;

public class Pitch {

    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int number;

    private Pitch(final int number) {
        if (number < MIN || number > MAX) {
            throw new PitchResultCreateException();
        }

        this.number = number;
    }

    public static Pitch of(final int number) {
        return new Pitch(number);
    }

    public static Pitch of(final String number) {
        try {
            return new Pitch(Integer.parseInt(number));
        } catch (final Exception ex) {
            throw new PitchResultCreateException();
        }
    }

    public static Pitch zero() {
        return new Pitch(0);
    }

    public int add(final Pitch pitchResult) {
        return this.number + pitchResult.number;
    }

    public boolean isStrike() {
        return number == MAX;
    }

    public int getNumber() {
        return number;
    }
}
