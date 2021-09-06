package bowling.view;

import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.SparePitch;
import bowling.domain.pitch.StrikePitch;

public enum ResultFlag {

	STRIKE("X"),
	SPARE("/"),
	GUTTER("-");

	private static final Pins GUTTER_PINS = Pins.of(0);

	private final String flag;

	ResultFlag(final String flag) {
		this.flag = flag;
	}

	public static String generateResultByFrame(final Frame frame, final String delimiter) {
		return frame.pitches()
			.stream()
			.map(ResultFlag::generateResultByPitch)
			.collect(Collectors.joining(delimiter));
	}

	private static String generateResultByPitch(final Pitch pitch) {
		if (pitch instanceof StrikePitch) {
			return STRIKE.flag;
		}

		if (pitch instanceof SparePitch) {
			return SPARE.flag;
		}

		if (pitch.getPins().isEqualsCount(GUTTER_PINS)) {
			return GUTTER.flag;
		}

		return String.valueOf(pitch.getPins().count());
	}
}
