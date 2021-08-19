package bowling.domain.frame;

import java.util.Optional;

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

    private LastFrame(final PitchResult first, final PitchResult second, final PitchResult bonus) {
        super(first, second);
        this.bonus = bonus;
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

    public static LastFrame of(final PitchResult first, final PitchResult second, final PitchResult bonus) {
        return new LastFrame(first, second, bonus);
    }

    @Override
    public void pitch(final PitchResult result) {
        if (first != null && second != null) {
            bonus = result;
        }

        final Optional<PitchResult> first = Optional.ofNullable(this.first);

        if (!first.isPresent()) {
            this.first = result;
            return;
        }

        first.ifPresent(e -> addSecond(result));
    }

    public boolean possibleBouns() {
        return isStrike() || isSpare();
    }

    public int getBonus() {
        return bonus.getNumber();
    }
}
