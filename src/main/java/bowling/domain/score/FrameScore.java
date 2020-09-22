package bowling.domain.score;

import bowling.domain.frame.FrameState;
import bowling.domain.roll.Roll;

public class FrameScore implements ScoreObserver {

    private final Score score = new Score();
    private int numberOfAdd;

    public FrameScore(FrameState frameState, int initialScore) {
        numberOfAdd = frameState.getCountOfBonus();
        score.add(initialScore);
    }

    public boolean addable() {
        return numberOfAdd > 0;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean update(Roll roll) {
        if (addable()) {
            numberOfAdd--;
            this.score.add(roll);
            return true;
        }
        return false;
    }
}
