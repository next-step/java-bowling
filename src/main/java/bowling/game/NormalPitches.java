package bowling.game;

import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class NormalPitches implements Pitches {
    private static final int PITCH_COUNT_MAX = 2;

    private final LinkedList<Pitch> pitches;

    public NormalPitches() {
        this.pitches = new LinkedList<>();
    }

    @Override
    public int throwBall(final int pinCount) {
        if (pitches.isEmpty()) {
            pitches.add(Pitch.firstPitch(pinCount));

            return 10 - pitches.stream()
                    .mapToInt(Pitch::getPinCount)
                    .sum();
        }

        Pitch lastPitch = pitches.getLast();
        pitches.add(lastPitch.nextPitch(pinCount));

        return 10 - pitches.stream()
                .mapToInt(Pitch::getPinCount)
                .sum();
    }

    @Override
    public boolean hasChance() {
        if (pitches.isEmpty()) {
            return true;
        }

        Pitch firstPitch = pitches.getFirst();

        if (firstPitch.isStrikePitch()) {
            return false;
        }

        if (pitches.size() == PITCH_COUNT_MAX) {
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

    @Override
    public boolean isStrikePitches() {
        return pitches.getFirst().isStrikePitch();
    }

    @Override
    public boolean isSparePitches() {
        return pitches.getLast().isSparePitch();
    }

    @Override
    public int getBasicScore() {
        if (this.isStrikePitches() || this.isSparePitches()) {
            return 10;
        }

        return pitches.stream()
                .mapToInt(Pitch::getPinCount)
                .sum();
    }
}
