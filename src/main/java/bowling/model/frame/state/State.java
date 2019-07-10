package bowling.model.frame.state;

import bowling.model.Pins;

public interface State {

    State bowl(Pins pins);

    boolean isFinished();

    String printResult();
}
