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
        Pitch newPitch = getLast()
                .map(last -> last.next(pinCount))
                .orElse(new Pitch(pinCount));

        return add(newPitch);
    }

    public Score getScore() {
        int sum = pitches.stream()
                .map(Pitch::getPinCount)
                .reduce(0, Integer::sum);

        return new Score(sum, needAdditionalScore());
    }

    public Optional<Score> getFirstPitchScore() {
        return get(ZERO).map(p -> new Score(p.getPinCount()));
    }

    public Optional<Score> getSecondPitchScore() {
        return get(ONE).map(p -> new Score(p.getPinCount()));
    }

    public boolean isSecondPitchSpare() {
        Optional<Pitch> pitch = get(ONE);
        return pitch.map(Pitch::isSpare).orElse(false);
    }

    public boolean isFirstPitchStrike() {
        Optional<Pitch> pitch = get(ZERO);
        return pitch.map(Pitch::isStrike).orElse(false);
    }

    public int size() {
        return pitches.size();
    }

    private boolean needAdditionalScore() {
        return getLast()
                .map(lastPitch -> lastPitch.isSpare() || lastPitch.isStrike())
                .orElse(false);
    }

    private Optional<Pitch> get(int index) {
        if (pitches.size() <= index) {
            return Optional.empty();
        }

        return Optional.of(pitches.get(index));
    }

    private Optional<Pitch> getLast() {
        if (pitches.isEmpty()) {
            return Optional.empty();
        }
        return get(getLastIndex());
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
