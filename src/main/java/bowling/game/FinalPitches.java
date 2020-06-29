package bowling.game;

import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class FinalPitches implements Pitches {
    private static final int PITCH_COUNT_MAX = 2;
    private static final int BONUS_COUNT_MAX = 3;

    private final LinkedList<Pitch> pitches;

    public FinalPitches() {
        this.pitches = new LinkedList<>();
    }

    @Override
    public int throwBall(final int pinCount) {
        if (pitches.isEmpty() || isBonusPitch()) {
            pitches.add(Pitch.firstPitch(pinCount));

            return 10 - pinCount;
        }

        Pitch lastPitch = pitches.getLast();
        pitches.add(lastPitch.nextPitch(pinCount));

        return 10 - pitches.stream()
                .mapToInt(Pitch::getPinCount)
                .sum();
    }

    @Override
    public boolean hasChance() {
        if (pitches.size() == PITCH_COUNT_MAX && !isBonusPitch()) {
            return false;
        }

        if (pitches.size() == BONUS_COUNT_MAX) {
            return false;
        }

        return true;
    }

    @Override
    public String getPitchesStates() {
        return pitches.stream()
                .map(Pitch::stateToString)
                .collect(joining("|"));
    }

    private boolean isBonusPitch() {
        return pitches.getFirst().isStrikePitch() || pitches.getLast().isSparePitch();
    }
}
