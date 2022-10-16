package bowling.domain.state;

import bowling.domain.Score;

import java.util.List;

public interface State {
    boolean isFinish();

    State bowl(Score score);

    int bonusCount();

    boolean canGetBonus();

    int getRemainPins();

    BowlingRecordState getBowlingState();

    List<Integer> getRecord();

}
