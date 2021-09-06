package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.common.Score;
import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.pitch.Pitch;

public final class NormalFrame extends BaseFrame {

	private static final int MAX_PITCHES_COUNT = 2;

	private NormalFrame(final List<Pitch> pitches) {
		super(pitches);
	}

	public static Frame of() {
		return new NormalFrame(Collections.emptyList());
	}

	@Override
	public boolean possiblePitch() {
		return !(pitches.size() == MAX_PITCHES_COUNT || isFirstStrike());
	}

	@Override
	public Frame pitch(final Pins pins) {
		if (!possiblePitch()) {
			throw new InvalidProgressFrameException();
		}

		return new NormalFrame(playedPitches(pins));
	}

	@Override
	public Frame next() {
		return NormalFrame.of();
	}

	@Override
	public Frame last() {
		return FinalFrame.of();
	}

	@Override
	public Score caculateScore(final Frames frames) {
		if (possiblePitch()) {
			return playingScore();
		}

		final Score score = playedScore();
		if (score.possiblecalculate()) {
			return score;
		}

		return nextCalculatedScore(score, frames);
	}

	private Score playingScore() {
		final int pinsCountSum = pitches().stream()
			.map(Pitch::getPins)
			.mapToInt(Pins::count)
			.sum();
		final int leftCount = MAX_PITCHES_COUNT - pitches().size();

		return new Score(pinsCountSum, leftCount);
	}

	private Score playedScore() {
		if (isFirstStrike()) {
			return Score.strike();
		}

		if (isSecondSpare()) {
			return Score.spare();
		}

		return Score.miss(pitches().stream()
			.map(Pitch::getPins)
			.mapToInt(Pins::count)
			.sum());
	}

	@Override
	public Score additionalScore(Score beforeScore, final Frames frames) {
		final List<Pitch> limitedPitches = pitches().stream()
			.limit(beforeScore.getLeftCount())
			.collect(Collectors.toList());

		for (final Pitch pitch : limitedPitches) {
			beforeScore = beforeScore.plus(pitch);
		}

		if (beforeScore.possiblecalculate()) {
			return beforeScore;
		}

		return nextCalculatedScore(beforeScore, frames);
	}

	private Score nextCalculatedScore(final Score score, final Frames frames) {
		final Frame nextFrame = nextFrame(frames);

		if (nextFrame == null) {
			return score;
		}

		return nextFrame.additionalScore(score, frames);
	}

	private Frame nextFrame(final Frames frames) {
		try {
			return frames.get(frames.indexOf(this) + 1);
		} catch (final IndexOutOfBoundsException e) {
			return null;
		}
	}
}
