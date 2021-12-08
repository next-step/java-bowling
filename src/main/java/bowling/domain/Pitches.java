package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Pitch.*;

public class Pitches {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    public static final int NUMBER_OF_PITCHES_AFTER_FIRST_PITCH = 1;
    public static final int NUMBER_OF_PITCHES_AFTER_SECOND_PITH = 2;
    private static final Pitch STRIKE_PITCH = new Pitch(STRIKE_PIN_NUMBER);
    private static final Pitch GUTTER_PITCH = new Pitch(GUTTER_PIN_NUMBER);
    private static final char SPLITERATOR = '|';

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
        return pitches.size() == NUMBER_OF_PITCHES_AFTER_FIRST_PITCH;
    }

    public boolean isStrikeOrSpare() {
        return isStrike() || isSpare();
    }

    public boolean isStrike() {
        return !pitches.isEmpty() && pitches.get(Pitches.FIRST).equals(STRIKE_PITCH);
    }

    public boolean isSpare() {
        if (pitches.size() <= NUMBER_OF_PITCHES_AFTER_FIRST_PITCH) {
            return false;
        }

        Pitch currentPitch = pitches.get(SECOND);
        Pitch previousPitch = pitches.get(FIRST);

        return previousPitch.score().add(currentPitch.score()).equals(new Score(10));
    }

    public String state() {
        if (isFirstPitch()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstPitch().state(GUTTER_PITCH));

        if (isSecondPitch() || isStrike()) {
            return stringBuilder.toString();
        }

        stringBuilder.append(SPLITERATOR);
        stringBuilder.append(secondPitch().state(firstPitch()));

        return stringBuilder.toString();
    }

    public String finalState() {
        if (isFirstPitch()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstPitch().state(GUTTER_PITCH));

        if (isSecondPitch()) {
            return stringBuilder.toString();
        }
        stringBuilder.append(SPLITERATOR);
        stringBuilder.append(secondPitch().state(firstPitch()));
        if (pitches.size() == NUMBER_OF_PITCHES_AFTER_SECOND_PITH || !isStrikeOrSpare()) {
            return stringBuilder.toString();
        }
        stringBuilder.append(SPLITERATOR);
        stringBuilder.append(thirdPitch().state(secondPitch()));

        return stringBuilder.toString();
    }

    private Pitch firstPitch() {
        return pitches.get(Pitches.FIRST);
    }

    private Pitch secondPitch() {
        return pitches.get(Pitches.SECOND);
    }

    private Pitch thirdPitch() {
        return pitches.get(Pitches.THIRD);
    }
}
