package bowling.domain.frame;

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
    protected List<Pitch> pitches;

    protected DefaultFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    protected boolean isFirstPitch() {
        return pitches.isEmpty();
    }

    protected boolean isSecondPitch() {
        return !pitches.isEmpty() && pitches.size() == 1 && !pitches.get(0).getKnockedPins().isStrike();
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
        return pitches.get(pitches.size() - 1).play(knockedPins);
    }

    public DefaultFrame createNextFrame() {
        return NormalFrame.init();
    }

    public DefaultFrame createLastFrame() {
        return new LastFrame(Collections.emptyList());
    }

    public String convert() {
        if (pitches.size() == 0) {
            return "";
        }
        if (pitches.get(0).getKnockedPins().isStrike()) {
            return "X";
        }
        if (pitches.size() == 1) {
            return pitches.get(0).getKnockedPins().convert() + "|";
        }
        if (isSpare(pitches.get(0).getKnockedPins(), pitches.get(1).getKnockedPins())) {
            return pitches.get(0).getKnockedPins().convert() + "|" + "/";
        }
        return pitches.get(0).getKnockedPins().convert() + "|" + pitches.get(1).getKnockedPins().convert();
    }
    protected Score playingScore() {
        return new Score(
                getPitches().stream()
                        .map(Pitch::getKnockedPins)
                        .map(KnockedPins::getKnockedPins)
                        .reduce(0, Integer::sum),
                maxPitchesCount() - getPitches().size()
        );
    }

    protected abstract int maxPitchesCount();

    @Override
    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
