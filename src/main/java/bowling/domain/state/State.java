package bowling.domain.state;

import bowling.domain.pin.FallenPin;

public interface State {
    State bowl(FallenPin fallenPin);

    boolean isFinished();

    String description();

    int tries();
}
