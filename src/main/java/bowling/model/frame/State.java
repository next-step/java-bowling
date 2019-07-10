package bowling.model.frame;

import bowling.model.Pins;

public interface State {

    State bowl(Pins pins);

    boolean isFinished();

    String printResult();
}
