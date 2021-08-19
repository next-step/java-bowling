package bowling.domain.pitch;


import bowling.domain.pitch.exception.PitchResultCreateException;

public class Pitch {

    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int number;
    private final PitchResult pitchResult;

    private Pitch(final int number) {
        if (number < MIN || number > MAX) {
            throw new PitchResultCreateException();
        }

        this.number = number;
        this.pitchResult = PitchResult.findByPitch(number);
    }

    private Pitch(final Pitch first, final int number) {
        if (number < MIN || number > MAX) {
            throw new PitchResultCreateException();
        }

        if (first.getNumber() + number > MAX) {
            throw new PitchResultCreateException();
        }

        this.number = number;
        this.pitchResult = PitchResult.findByPitch(first.getNumber(), number);
    }

    public static Pitch first(final int number) {
        return new Pitch(number);
    }

    public static Pitch first(final String number) {
        try {
            return new Pitch(Integer.parseInt(number));
        } catch (final Exception ex) {
            throw new PitchResultCreateException();
        }
    }

    public Pitch second(final int number) {
        return new Pitch(this, number);
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

    public PitchResult getPitchResult() {
        return pitchResult;
    }
}
