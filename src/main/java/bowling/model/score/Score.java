package bowling.model.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bowling.model.Pin;

public class Score extends ScoreStatus {

	private final int score;
	private final int left;

	private Score(int left) {
		super(new ArrayList<>());
		score = 0;
		this.left = left;
	}

	private Score(List<Pin> frameScore, int score, int left) {
		super(frameScore);
		this.score = score;
		this.left = left;
	}

	public Score Double() {
		return new Score(getFrameScore(), 20, 1);
	}

	public static Score init() {
		return new Score(-1);
	}

	public static Score strike(List<Pin> frameScore) {
		return new Score(frameScore, 10, 2);
	}

	public static Score spare(List<Pin> frameScore) {
		return new Score(frameScore, 10, 1);
	}

	public static Score miss(List<Pin> frameScore, int score) {
		return new Score(frameScore, score, 0);
	}

	public static Score nothing(List<Pin> frameScore) {
		return new Score(frameScore, 0, -1);
	}

	public boolean isMiss() {
		return left == 0;
	}

	public int calculate(Score score) {
		if (getFrameScore(score.getLeft()) == -1) {
			return getFrameScore(score.getLeft());
		}
		return getFrameScore(score.getLeft()) + score.getScore();
	}

	public int getScore() {
		return this.score;
	}

	public boolean moveNotNextCalculate(Score score) {
		return isDoubleCondition() && score.getLeft() == 2;
	}

	public int getLeft() {
		return left;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score1 = (Score)o;
		return score == score1.score && left == score1.left;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, left);
	}
}
