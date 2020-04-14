package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public interface Frame {

    boolean addPinCount(int nextPinCount);

    Optional<Integer> getScore();

    boolean isDone();

    List<Pitch> getPitches();

    Frame createNext();

    Frame getNext();

    boolean isLast();

    Optional<Integer> getScoreForOnePitch();

    Optional<Integer> getScoreForTwoPitches();
}
