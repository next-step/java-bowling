package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Frame {
    private static final int STRIKE_PINS_COUNT = 10;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    protected final List<Pitch> pitches;

    protected Frame(final List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public abstract boolean playing();

    public abstract Frame play(KnockedPins knockedPins);

    public abstract Frame next();

    public abstract Frame last();

    protected abstract int maxPitchesCount();

    public List<Pitch> pitches() {
        return Collections.unmodifiableList(pitches);
    }

    protected void validatePitchesFull() {
        if (isPitchesFull()) {
            throw new IllegalArgumentException(String.format("%d번 이상 투구를 진행할 수 없습니다.", maxPitchesCount()));
        }
    }

    protected List<Pitch> playedPitches(final KnockedPins knockedPins) {
        final Pitch playedPitch = pitches.isEmpty() ?
                firstPitch(knockedPins) :
                otherPitch(knockedPins);

        return Stream.concat(
                pitches.stream(),
                Stream.of(playedPitch)
        ).collect(Collectors.toList());
    }

    protected Pitch firstPitch(final KnockedPins knockedPins) {
        if (knockedPins.count() == STRIKE_PINS_COUNT) {
            return new Strike();
        }
        return new Normal(knockedPins);
    }

    protected Pitch otherPitch(final KnockedPins knockedPins) {
        return pitches.get(pitches.size() - 1).play(knockedPins);
    }

    protected boolean isPitchesFull() {
        return pitches.size() == maxPitchesCount();
    }

    protected boolean isFirstStrike() {
        return pitches.size() > FIRST_INDEX && pitches.get(FIRST_INDEX) instanceof Strike;
    }

    protected boolean isSecondSpare() {
        return pitches.size() > SECOND_INDEX && pitches.get(SECOND_INDEX) instanceof Spare;
    }
}
