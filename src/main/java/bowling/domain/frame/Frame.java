package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.List;

public interface Frame {
    boolean playing();

    Frame play(int knockedPinsCount);

    Frame play(KnockedPins knockedPins);

    Frame next();

    Frame last();

    List<Pitch> pitches();

    Score score(List<Frame> frames);

    Score additionalScore(Score beforeScore, List<Frame> frames);
}
