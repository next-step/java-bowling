package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Pitch.*;

public class Pitches {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    public static final int NUMBER_OF_PITCH_FOR_BEGINNING = 0;
    public static final int NUMBER_OF_PITCH_FOR_SECOND = 1;
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

    public boolean full(int max) {
        return pitches.size() == max;
    }

    public boolean isFirstPitch() {
        return pitches.isEmpty();
    }

    public boolean isSecondPitch() {
        return pitches.size() == NUMBER_OF_PITCH_FOR_SECOND;
    }

    public boolean containingFirstStike() {
        return !pitches.isEmpty() && isStrike();
    }

    public boolean containingSpare() {
        return pitches.size() > NUMBER_OF_PITCH_FOR_SECOND && isSpare(SECOND);
    }

    public boolean containingStrikeOrSpare() {
        return isStrike() || isSpare(SECOND);
    }

    private boolean isStrike() {
        return !empty() && pitches.get(Pitches.FIRST).equals(STRIKE_PITCH);
    }

    private boolean isSpare(int index) {
        if (pitches.size() <= NUMBER_OF_PITCH_FOR_SECOND || index < SECOND) {
            return false;
        }

        return prevPitch(index).score().add(currentPitch(index).score()).equals(new Score(10));
    }

    private Pitch currentPitch(int index) {
        return pitches.get(index);
    }

    private Pitch prevPitch(int index) {
        return pitches.get(index - 1);
    }

    private boolean empty() {
        return pitches.isEmpty();
    }

}
