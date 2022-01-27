package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
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
        if (knockedPins.getKnockedPins() == 10) {
            return new Strike(knockedPins);
        }
        return new Normal(knockedPins);
    }

    protected Pitch otherPitch(final KnockedPins knockedPins) {
        System.out.println("otherPitch");
        System.out.println("pitches.get(pitches.size() - 1).play(knockedPins) = "+pitches.get(pitches.size() - 1).play(knockedPins));
        return pitches.get(pitches.size() - 1).play(knockedPins);
    }

    public DefaultFrame createNextFrame() {
        return NormalFrame.init();
    }

    public DefaultFrame createFinalFrame() {
        return FinalFrame.init();
    }

    public abstract String convert();


    protected abstract int maxPitchesCount();

    @Override
    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
