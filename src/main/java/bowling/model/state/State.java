package bowling.model.state;

import bowling.model.Pins;

public interface State {

    State pitch(Pins pins);

    boolean isFrameEnd();

    String getSymbol();

}
