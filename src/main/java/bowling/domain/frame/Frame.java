package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Optional;

public interface Frame {

    boolean addPinCount(int nextPinCount);

    Optional<Integer> getScore();

    boolean isDone();

    List<Pitch> getPitches();

    Frame createNext();

    Frame createNext(Frame frame);

    boolean isLast();

    Optional<Score> getScoreForOnePitch();

    Optional<Score> getScoreForTwoPitches();
}
