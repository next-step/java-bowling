package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.common.Score;
import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.pitch.Pitch;

public final class FinalFrame extends BaseFrame {

	private static final int MAX_PITCHES_COUNT = 3;

	private FinalFrame(final List<Pitch> pitches) {
		super(pitches);
	}

	public static Frame of() {
		return new FinalFrame(Collections.emptyList());
	}

	@Override
	public boolean possiblePitch() {
		if (pitches.size() == MAX_PITCHES_COUNT) {
			return false;
		}

		return pitches().size() < MAX_PITCHES_COUNT - 1
			|| isFirstStrike() || isSecondSpare();
	}

	@Override
	public Frame pitch(final Pins pins) {
		if (!possiblePitch()) {
			throw new InvalidProgressFrameException();
		}

		return new FinalFrame(playedPitches(pins));
	}

	@Override
	public Score caculateScore(final Frames frames) {
		final int pinsCountSum = pitches().stream()
			.map(Pitch::getPins)
			.mapToInt(Pins::count)
			.sum();
		final int leftCount = possiblePitch() ? MAX_PITCHES_COUNT - pitches().size() : 0;

		return new Score(pinsCountSum, leftCount);
	}

	@Override
	public Score additionalScore(Score beforeScore, final Frames frames) {
		final List<Pitch> limitedPitches = pitches().stream()
			.limit(beforeScore.getLeftCount())
			.collect(Collectors.toList());

		for (final Pitch pitch : limitedPitches) {
			beforeScore = beforeScore.plus(pitch);
		}

		return beforeScore;
	}
}
