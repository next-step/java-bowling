package bowling.domain.frame;

import static bowling.domain.pitch.PitchResult.STRIKE;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.PitchResult;
import java.util.Optional;

public class LastFrame extends Frame {

    protected Pitch bonus;

    private LastFrame() {
        super();
    }

    private LastFrame(final Pitch first) {
        super(first);
    }

    private LastFrame(final Pitch first, final Pitch second) {
        super(first, second);
    }

    private LastFrame(final Pitch first, final Pitch second, final Pitch bonus) {
        super(first, second);
        this.bonus = bonus;
    }

    public static LastFrame of() {
        return new LastFrame();
    }

    public static LastFrame of(final Pitch first) {
        return new LastFrame(first);
    }

    public static LastFrame of(final Pitch first, final Pitch second) {
        return new LastFrame(first, second);
    }

    public static LastFrame of(final Pitch first, final Pitch second, final Pitch bonus) {
        return new LastFrame(first, second, bonus);
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
        if (first != null && second != null) {
            bonus = result;
        }

        final Optional<Pitch> first = Optional.ofNullable(this.first);

        if (!first.isPresent()) {
            this.first = result;
            return;
        }

        first.ifPresent(e -> addSecond(result));
    }

    public boolean possibleBouns() {
        return first.getPitchResult().equals(STRIKE)
            || first.getNumber() + second.getNumber() == MAX;
    }

    public Pitch getBonus() {
        return bonus;
    }
}
