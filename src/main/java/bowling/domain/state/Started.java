package bowling.domain.state;

import bowling.domain.Score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Started implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(Score score) {
        if (score.isStrike()) {
            return new Strike(score);
        }

        return new Running(score);
    }

    @Override
    public int bonusCount() {
        return 0;
    }

    @Override
    public boolean canGetBonus() {
        return false;
    }

    @Override
    public int getRemainPins() {
        return 10;
    }

    @Override
    public BowlingRecordState getBowlingState() {
        return BowlingRecordState.STARTED;
    }

    @Override
    public List<Integer> getRecord() {
        return Collections.unmodifiableList(new LinkedList<>());
    }
}
