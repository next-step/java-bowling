package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {

    boolean addPinCount(int nextPinCount);

    Score getScore();

    boolean isDone();

    List<Pitch> getFramePitch();

    Frame createNext();

    Frame createNext(Frame frame);

    boolean isLast();

    Score getScoreForOnePitch();

    Score getScoreForTwoPitches();
}
