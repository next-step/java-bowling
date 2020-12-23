package bowling.domain.state;

import bowling.domain.Pitch;

import java.util.List;

public abstract class Running implements State{

    private final int MAX_SCORE = 10;

    private final List<Pitch> pitches;

    protected Running(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public boolean isFinish() {
        return getScore() == MAX_SCORE;
    }

    public int getScore() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }

}
