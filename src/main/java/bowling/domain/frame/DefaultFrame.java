package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.pitch.Normal;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Strike;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DefaultFrame implements Frame {
    protected final List<Pitch> pitches;

    protected DefaultFrame(final List<Pitch> pitches) {
        this.pitches = pitches;
    }

    protected boolean isFirstPitch() {
        return pitches.isEmpty();
    }

    protected List<Pitch> playPitches(final KnockedPins knockedPins) {
        final Pitch playedPitch = pitches.isEmpty() ?
                firstPitch(knockedPins) :
                otherPitch(knockedPins);

        return Stream.concat(
                pitches.stream(),
                Stream.of(playedPitch)
        ).collect(Collectors.toList());
    }

    protected Pitch firstPitch(final KnockedPins knockedPins) {
        if (knockedPins.getCount() == 10) {
            return new Strike(knockedPins);
        }
        return new Normal(knockedPins);
    }

    protected Pitch otherPitch(final KnockedPins knockedPins) {
        return pitches.get(pitches.size() - 1).play(knockedPins);
    }

    public KnockedPins getFirstKnockedPins() {
        if (pitches.size() >= 1) {
            return pitches.get(0).getKnockedPins();
        }
        throw new IllegalArgumentException("no first count");
    }

    public KnockedPins getSecondKnockedPins() {
        if (pitches.size() >= 2) {
            return pitches.get(1).getKnockedPins();
        }
        throw new IllegalArgumentException("no second count");
    }

    public DefaultFrame createNextFrame() {
        return NormalFrame.init();
    }

    public DefaultFrame createFinalFrame() {
        return FinalFrame.init();
    }

    @Override
    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}