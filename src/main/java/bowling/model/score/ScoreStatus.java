package bowling.model.score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import bowling.model.Pin;

public abstract class ScoreStatus {

	private final List<Pin> frameScore;

	public ScoreStatus(List<Pin> frameScore) {
		this.frameScore = Collections.unmodifiableList(frameScore);
	}

	public boolean isDoubleCondition() {
		if (frameScore.size() == 0) {
			return false;
		}
		return frameScore.get(0).isMaxPin() && frameScore.size() == 1;
	}

	public int getFrameScore(int left) {
		if (frameScore.size() < left) {
			return -1;
		}
		return IntStream.range(0, left)
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
