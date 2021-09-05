package bowling.domain.frame;

import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.common.Score;
import bowling.domain.pitch.Pitch;

public interface Frame {

	boolean possiblePitch();

	Frame pitch(final int pinsCount);

	Frame pitch(final Pins pins);

	Frame next();

	Frame last();

	List<Pitch> pitches();

	Score caculateScore(List<Frame> frames);

	Score additionalScore(Score beforeScore, List<Frame> frames);
}
