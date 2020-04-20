package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FramePitch {
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private List<Pitch> pitches;

    public FramePitch() {
        this.pitches = new ArrayList<>();
    }

    public boolean add(Pitch pitch) {
        return pitches.add(pitch);
    }

    public boolean add(int pinCount) {
        Pitch newPitch = getLastPitchOrEmpty()
                .map(last -> last.next(pinCount))
                .orElse(new Pitch(pinCount));

        return add(newPitch);
    }

    public int getPinCountTotal() {
        return pitches.stream()
                .map(Pitch::getPinCount)
                .reduce(0, Integer::sum);
    }

    public Optional<Integer> getFirstPitchPinCount() {
        return getOrEmpty(ZERO).map(Pitch::getPinCount);
    }

    public Optional<Integer> getSecondPitchPinCount() {
        return getOrEmpty(ONE).map(Pitch::getPinCount);
    }

    public boolean isSecondPitchSpare() {
        Optional<Pitch> pitch = getOrEmpty(ONE);
        return pitch.map(Pitch::isSpare).orElse(false);
    }

    public boolean isFirstPitchStrike() {
        Optional<Pitch> pitch = getOrEmpty(ZERO);
        return pitch.map(Pitch::isStrike).orElse(false);
    }

    public int size() {
        return pitches.size();
    }

    private Optional<Pitch> getOrEmpty(int index) {
        if (pitches.size() <= index) {
            return Optional.empty();
        }

        return Optional.of(pitches.get(index));
    }

    private Optional<Pitch> getLastPitchOrEmpty() {
        if (pitches.isEmpty()) {
            return Optional.empty();
        }
        return getOrEmpty(getLastIndex());
    }

    public Pitch getLastPitch() {
        return pitches.get(getLastIndex());
    }

    private int getLastIndex() {
        return pitches.size() - ONE;
    }

    public List<Pitch> getPitches() {
        return pitches.stream()
                .map(Pitch::new)
                .collect(Collectors.toList());
    }
}
