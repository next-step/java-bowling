package bowling.model.state;

import bowling.model.Pins;

public interface State {
    State bowl(Pins knockedDownPin);
    boolean isFinish();
    String getDesc();
}
