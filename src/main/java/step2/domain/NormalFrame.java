package step2.domain;

import step2.domain.state.Miss;
import step2.domain.state.Spare;
import step2.domain.state.State;
import step2.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private static final int MAX_SIZE = 2;

    private final List<Pitch> pitches;

    public NormalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Frame init() {
        List<Pitch> pitches = new ArrayList<>(MAX_SIZE);
        return new NormalFrame(pitches);
    }

    @Override
    public void bowl(Pitch pitch) {
        pitches.add(pitch);
    }

    @Override
    public boolean isFinish() {
        return isStrike() || isMaxSize();
    }

    private boolean isStrike() {
        return pitches.get(0).getScore() == Pitch.MAX_SCORE;
    }

    private boolean isSpare() {
        int totalScore = pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
        return totalScore == Pitch.MAX_SCORE;
    }

    private boolean isMaxSize() {
        return getSize() == MAX_SIZE;
    }

    public State getState() {
        if (isStrike()) {
            return new Strike();
        }

        if (isSpare()) {
            return new Spare(pitches.get(0));
        }

        return new Miss(pitches.get(0), pitches.get(1));
    }

    @Override
    public int getSize() {
        return pitches.size();
    }
}
