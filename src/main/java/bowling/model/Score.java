package bowling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.model.frame.Frame;

public class Score {

	private static final int ZERO_POINT = 0;

	private final List<Integer> score;

	private Score(List<Integer> score) {
		this.score = Collections.unmodifiableList(score);
	}

	public static Score createScore(List<Frame> frames) {
		List<Integer> score = new ArrayList<>();
		int totalScore = ZERO_POINT;
		for (Frame frame : frames) {
			totalScore = sumScore(totalScore, frame, score);
		}
		return new Score(score);
	}

	private static int sumScore(int totalScore, Frame frame, List<Integer> score) {
		if (frame.isGameEnd() && frame.getGameScore() > -1) {
			score.add(totalScore + frame.getGameScore());
			return totalScore + frame.getGameScore();
		}
		score.add(-1);
		return totalScore;
	}

	public List<Integer> getScore() {
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score1 = (Score)o;
		return Objects.equals(score, score1.score);
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
