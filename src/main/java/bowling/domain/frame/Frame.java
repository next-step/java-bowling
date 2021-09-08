package bowling.domain.frame;

import bowling.domain.Pins;

public interface Frame {
    boolean isEnd();
    Pins pitch(int countOfPins);
    String valueOfFrame();
}
