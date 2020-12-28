package step2.domain;

import step2.domain.state.Miss;
import step2.domain.state.Spare;
import step2.domain.state.State;
import step2.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private static final int MIN_SIZE = 1;
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
        if (getSize() == MIN_SIZE) {
            validateScore(pitch);
        }
        pitches.add(pitch);
    }

    @Override
    public boolean isFinish() {
        if (getSize() < MIN_SIZE) {
            return false;
        }
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

        if (getSize() == MIN_SIZE) {
            return new Miss(pitches.get(0));
        }

        if (isSpare()) {
            return new Spare(pitches.get(0));
        }

        return new Miss(pitches.get(0), pitches.get(1));
    }

    @Override
    public void validateScore(Pitch pitch) {
        int totalScore = pitches.get(0).getScore() + pitch.getScore();
        if (totalScore > Pitch.MAX_SCORE) {
            throw new IllegalArgumentException("한 프레임의 점수는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public int getSize() {
        return pitches.size();
    }
}
