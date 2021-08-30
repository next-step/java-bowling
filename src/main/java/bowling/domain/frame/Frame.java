package bowling.domain.frame;

import java.util.List;

import bowling.domain.common.KnockedPins;
import bowling.domain.pitch.Pitch;

public interface Frame {

	boolean possiblePitch();

	Frame pitch(final int knockedPinsCount);

	Frame pitch(final KnockedPins knockedPins);

	Frame next();

	Frame last();

	List<Pitch> pitches();
}
