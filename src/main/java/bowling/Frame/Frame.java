package bowling.Frame;

import bowling.bowl.Bowl;
import bowling.pin.Pins;

public interface Frame {
    Frame pitch(Pins pins);
    int getIndex();
    Bowl getBowls();
    boolean hasNext();
    String getSymbol();
}
