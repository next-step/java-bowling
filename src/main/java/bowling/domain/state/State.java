package bowling.domain.state;

import bowling.domain.Frame;
import bowling.domain.Pins;

public interface State {
    void pitch(Pins existPins, Pins fallDownPins, Frame frame);
}
