package bowling.domain.frame;

import java.util.List;

import bowling.domain.common.FalledPins;
import bowling.domain.common.Score;
import bowling.domain.pitch.Pitch;

public interface Frame {

	boolean possiblePitch();

	Frame pitch(final int falledPinsCount);

	Frame pitch(final FalledPins falledPins);

	Frame next();

	Frame last();

	List<Pitch> pitches();

	Score caculateScore(List<Frame> frames);

	Score additionalScore(Score beforeScore, List<Frame> frames);
}
