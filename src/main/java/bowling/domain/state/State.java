package bowling.domain.state;

import bowling.domain.Pins;

public interface State {

    State bowl(Pins pins);

    String mark();

    boolean isFinished();

}
