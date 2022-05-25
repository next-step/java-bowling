package bowling.bowl;

import bowling.pin.Pins;

public interface Bowl {

    boolean isEnd();

    Bowl pitch(Pins pins);

    String getSymbol();
}
