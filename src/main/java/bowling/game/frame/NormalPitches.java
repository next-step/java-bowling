package bowling.game.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class NormalPitches implements Pitches {
    private static final int PITCH_COUNT_MAX = 2;

    private final LinkedList<Pitch> pitches;

    public NormalPitches() {
        this.pitches = new LinkedList<>();
    }

    @Override
    public void throwBall(final int pinCount) {
        if (pitches.isEmpty()) {
            pitches.add(Pitch.firstPitch(pinCount));
            return;
        }

        Pitch lastPitch = pitches.getLast();
        pitches.add(lastPitch.nextPitch(pinCount));
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

    public List<Integer> getPitchesPinCounts() {
        return pitches.stream()
                .map(Pitch::getPinCount)
                .collect(Collectors.toList());
    }
}
