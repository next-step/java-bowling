package bowling.domain.state;

import bowling.domain.Pins;

public interface State {

    State pitch(Pins pins);

    boolean isFrameEnd();

    String getSymbol();

}
