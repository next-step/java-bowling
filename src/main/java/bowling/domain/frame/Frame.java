package bowling.domain.frame;

import bowling.domain.Pins;

public interface Frame {
    Frame bowl(Pins pins);

    int getIndex();

    boolean isEnd();

    String symbol();
}
