package bowling.domain.frame;

public class Frame {

    private final PitchResult first;
    private PitchResult second;

    private Frame(final PitchResult first, final PitchResult second) {
        createValidation(first, second);

        this.first = first;
        this.second = second;
    }

    private void createValidation(final PitchResult first, final PitchResult second) {
        first.add(second);
    }

    private Frame(final PitchResult first) {
        this.first = first;
    }

    public static Frame of(final PitchResult first, final PitchResult second) {
        return new Frame(first, second);
    }

    public static Frame of(final PitchResult first) {
        return new Frame(first);
    }

    public Frame second(final PitchResult second) {
        return Frame.of(this.first, second);
    }
}
