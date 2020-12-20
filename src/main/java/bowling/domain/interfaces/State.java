package bowling.domain.interfaces;

import bowling.domain.Condition;
import bowling.domain.Pins;

public interface State {
    State bowl(int count);

    Pins getPins();

    Condition getCondition();
}
