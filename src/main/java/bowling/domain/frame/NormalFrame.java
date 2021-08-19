package bowling.domain.frame;

public class NormalFrame extends Frame {

    private NormalFrame() {
        super();
    }

    private NormalFrame(final PitchResult first) {
        super(first);
    }

    private NormalFrame(final PitchResult first, final PitchResult second) {
        super(first, second);
    }

    public static NormalFrame of() {
        return new NormalFrame();
    }

    public static NormalFrame of(final PitchResult first) {
        return new NormalFrame(first);
    }

    public static NormalFrame of(final PitchResult first, final PitchResult second) {
        return new NormalFrame(first, second);
    }
}
