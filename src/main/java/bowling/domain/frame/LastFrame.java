package bowling.domain.frame;

public class LastFrame extends Frame {

    protected PitchResult bonus;

    private LastFrame() {
        super();
    }

    private LastFrame(final PitchResult first) {
        super(first);
    }

    private LastFrame(final PitchResult first, final PitchResult second) {
        super(first, second);
    }

    public static LastFrame of() {
        return new LastFrame();
    }

    public static LastFrame of(final PitchResult first) {
        return new LastFrame(first);
    }

    public static LastFrame of(final PitchResult first, final PitchResult second) {
        return new LastFrame(first, second);
    }

    public void updateBonus(final PitchResult result) {
        bonus = result;
    }
}
