package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import java.util.Optional;

public class NormalFrame extends Frame {

    private NormalFrame() {
        super();
    }

    private NormalFrame(final Pitch first) {
        super(first);
    }

    private NormalFrame(final Pitch first, final Pitch second) {
        super(first, second);
    }

    public static NormalFrame of() {
        return new NormalFrame();
    }

    public static NormalFrame of(final Pitch first) {
        return new NormalFrame(first);
    }

    public static NormalFrame of(final Pitch first, final Pitch second) {
        return new NormalFrame(first, second);
    }

    @Override
    public boolean isStrike() {
        return first.isStrike();
    }

    @Override
    public boolean isSpare() {
        return first.getNumber() + second.getNumber() == MAX;
    }

    @Override
    public void pitch(final Pitch result) {
        final Optional<Pitch> first = Optional.ofNullable(this.first);

        if (!first.isPresent()) {
            this.first = result;
            return;
        }

        first.ifPresent(e -> addSecond(result));
    }
}
