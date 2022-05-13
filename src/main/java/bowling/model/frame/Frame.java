package bowling.model.frame;

import bowling.model.Pins;

public interface Frame {

    boolean isFrameEnd();

    void pitch(Pins pins);

    String getSymbol();

}
