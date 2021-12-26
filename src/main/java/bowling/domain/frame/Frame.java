package bowling.domain.frame;

import bowling.domain.Pins;

public interface Frame {
    Frame bowl(Pins pins);

    boolean isEnd();

    int getIndex();

    String symbol();
}
