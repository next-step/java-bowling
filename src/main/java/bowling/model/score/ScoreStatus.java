package bowling.model.score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import bowling.model.Pin;

public abstract class ScoreStatus {

	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	
	private final List<Pin> frameScore;

	public ScoreStatus(List<Pin> frameScore) {
		this.frameScore = Collections.unmodifiableList(frameScore);
	}

	public boolean isDoubleCondition() {
		if (frameScore.size() == FIRST_INDEX) {
			return false;
		}
		return frameScore.get(FIRST_INDEX).isMaxPin() && frameScore.size() == SECOND_INDEX;
	}

	public int getFrameScore(int left) {
		if (frameScore.size() < left) {
			return -1;
		}
		return IntStream.range(FIRST_INDEX, left)
			.map(i -> frameScore.get(i).getPin())
			.sum();
	}

	protected List<Pin> getFrameScore() {
		return frameScore;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScoreStatus that = (ScoreStatus)o;
		return Objects.equals(frameScore, that.frameScore);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameScore);
	}
}
