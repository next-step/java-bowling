package bowling.domain.state;

import bowling.domain.value.Pins;

public interface State {
    State bowl(Pins pins);
    boolean isFinished();
    String getMark();

}
