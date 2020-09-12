package bowling.score;

import java.util.Objects;

import bowling.exception.BowlingScoreException;
import bowling.pitching.status.PitchingResult;

public class NormalScore implements Score {

	private int score;
	private int nextPitchingCountToReflect;
	private PitchingResult pitchingResult;

	private NormalScore(PitchingResult pitchingResult, int frameNo) {
		this.score = pitchingResult.getKnockingDownPins();
		this.nextPitchingCountToReflect = pitchingResult.getPitchingState().getNextPitchingCountToReflect(frameNo);
		this.pitchingResult = pitchingResult;
	}

	public static NormalScore of(PitchingResult pitchingResult, int frameNo) {
		return new NormalScore(pitchingResult, frameNo);
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int getNextPitchingCountToReflect() {
		return nextPitchingCountToReflect;
	}

	@Override
	public void addAdditionalScore(Score nextScore) {
		minusNextPitchingCountToReflect();
		this.score += nextScore.getScore();
	}

	@Override
	public boolean reflectPreviousScore(Score previousScore) {
		if (!previousScore.reflectableNextScore()) {
			return false;
		}

		this.score += previousScore.getScore();
		previousScore.reflectToNextScore();
		return true;
	}

	private void minusNextPitchingCountToReflect() {
		if (nextPitchingCountToReflect <= 0) {
			throw new BowlingScoreException("더 이상 점수를 반영할 수 없습니다.");
		}
		nextPitchingCountToReflect -= 1;
	}

	@Override
	public boolean isItTimeToReflectNextScores(int advancedPitchingCount) {
		return nextPitchingCountToReflect > 0 &&
			   advancedPitchingCount > 0 &&
			   nextPitchingCountToReflect == advancedPitchingCount;
	}

	@Override
	public boolean isDoneCalculates() {
		return pitchingResult.canMoveNextFrame() && nextPitchingCountToReflect == 0;
	}

	@Override
	public void reflectToNextScore() {
		pitchingResult.reflectToNextScore();
	}

	@Override
	public boolean isLastPitchingOfCurrentFrame() {
		return pitchingResult.isLastPitchingOfCurrentFrame();
	}

	@Override
	public boolean reflectableNextScore() {
		return nextPitchingCountToReflect == 0 && !pitchingResult.alreadyReflectToNextScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		NormalScore that = (NormalScore) o;
		return score == that.score &&
			   nextPitchingCountToReflect == that.nextPitchingCountToReflect &&
			   Objects.equals(pitchingResult, that.pitchingResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, nextPitchingCountToReflect, pitchingResult);
	}
}
