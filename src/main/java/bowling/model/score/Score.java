package bowling.model.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bowling.model.Pin;

public class Score extends ScoreStatus {

	private static final int SPARE_LEFT_NUMBER = 1;
	private static final int STRIKE_LEFT_NUMBER = 2;
	private static final int MISS_LEFT_NUMBER = 0;
	private static final int MAX_MIN = 10;
	private static final int DOUBLE_MAX_MIN = 20;
	private static final int ZERO_POINT = 0;

	private final int score;
	private final int left;

	private Score(int left) {
		super(new ArrayList<>());
		score = ZERO_POINT;
		this.left = left;
	}

	private Score(List<Pin> frameScore, int score, int left) {
		super(frameScore);
		this.score = score;
		this.left = left;
	}

	public Score Double() {
		return new Score(getFrameScore(), DOUBLE_MAX_MIN, SPARE_LEFT_NUMBER);
	}

	public static Score init() {
		return new Score(-1);
	}

	public static Score strike(List<Pin> frameScore) {
		return new Score(frameScore, MAX_MIN, STRIKE_LEFT_NUMBER);
	}

	public static Score spare(List<Pin> frameScore) {
		return new Score(frameScore, MAX_MIN, SPARE_LEFT_NUMBER);
	}

	public static Score miss(List<Pin> frameScore, int score) {
		return new Score(frameScore, score, MISS_LEFT_NUMBER);
	}

	public static Score nothing(List<Pin> frameScore) {
		return new Score(frameScore, ZERO_POINT, -1);
	}

	public boolean isMiss() {
		return left == ZERO_POINT;
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
		return isDoubleCondition() && score.getLeft() == STRIKE_LEFT_NUMBER;
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
