package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Pitch.*;

public class Pitches {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    public static final int NUMBER_OF_PITCH_FOR_BEGINNING = 0;
    public static final int NUMBER_OF_PITCH_FOR_STRIKE = 1;
    private static final int MAXIMUM_PITCH_FOR_NORMAL = 2;
    private static final Pitch STRIKE_PITCH = new Pitch(STRIKE_PIN_NUMBER);

    private final List<Pitch> pitches;

    public Pitches() {
        this(new ArrayList<>());
    }

    public Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public void add(Pitch pitch) {
        pitches.add(pitch);
    }

    public Score sum() {
        return pitches.stream().map(Pitch::score)
                .reduce(new Score(), Score::add);
    }

    public boolean continuableForNormalFrame() {
        return empty() || (pitches.size() < MAXIMUM_PITCH_FOR_NORMAL && !isStrike(FIRST));
    }

    public boolean continuableForFinalFrame() {
        return empty() || (pitches.size() < MAXIMUM_PITCH_FOR_NORMAL && isStrike(FIRST));
    }

    private boolean isStrike(int index) {
        return !empty() && pitches.get(index).equals(STRIKE_PITCH);
    }

    private boolean empty() {
        return pitches.isEmpty();
    }

}
