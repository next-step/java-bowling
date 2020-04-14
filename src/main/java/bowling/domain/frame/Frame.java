package bowling.domain.frame;

import java.util.List;

public interface Frame {
    boolean addPinCount(int nextPinCount);

    int getScore();

    boolean isDone();

    List<Pitch> getPitches();

    Frame createNext();

    Frame getNext();

    boolean isLast();
}
