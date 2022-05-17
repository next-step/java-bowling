package bowling.domain.frame;

import bowling.domain.Pins;

public interface Frame {

    boolean isFrameEnd();

    void pitch(Pins pins);

    String getSymbol();

}
