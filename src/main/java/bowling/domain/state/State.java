package bowling.domain.state;

import bowling.domain.Pin;

import java.util.List;

public interface State {
    boolean isFinish();

    State bowl(Pin pin);

    int bonusCount();

    boolean canGetBonus();

    int getRemainPins();

    List<Integer> getRecord();

    int getSum();

}
