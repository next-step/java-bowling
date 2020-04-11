package bowling.domain;

import java.util.List;

public interface Frame {
    void addPinCount(int nextPinCount);

    int getScore();

    boolean isDone();

    List<Integer> getPinCounts();

    Frame createNext();

    Frame getNext();

    boolean isLast();
}
