package bowling.domain;

import java.util.List;

public interface Frame {
    void addPinCount(PinCount nextPinCount);
    int getScore();
    boolean isDone();
    List<Integer> getPinCounts();

}
