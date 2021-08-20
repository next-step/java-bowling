package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.PitchResult;
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
    public boolean isEnd() {
        final Pitch first = Optional.ofNullable(this.first)
            .orElseGet(Pitch::zero);
        if (first.getPitchResult().equals(PitchResult.STRIKE)) {
            return true;
        }

        return this.first != null && this.second != null;
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
