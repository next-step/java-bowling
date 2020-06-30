package bowling.domain.score;

import java.util.Objects;

import bowling.domain.result.Result;
import bowling.util.ScoreBound;

public class Scores {

	private Score first;
	private Score second;
	private Score bonus;

	private Scores(Score first) {
		this.first = first;
	}

	private Scores() {
	}

	public static Scores from(Score first) {
		return new Scores(first);
	}

	public static Scores of() {
		return new Scores();
	}

	public void addFirstScore(Score firstScore) {
		if (Objects.nonNull(first)) {
			throw new IllegalArgumentException("이미 첫 번째 타구의 점수가 있습니다.");
		}
		this.first = firstScore;
	}

	public void addSecondScore(Score second) {
		first.add(second);
		this.second = second;
	}

	public void addBonusScore(Score bonus) {
		first.add(bonus);
		this.bonus = bonus;
	}

	public Score getFirst() {
		return first;
	}

	public Score getSecond() {
		return second;
	}

	public Score getBonus() {
		return bonus;
	}

	public Result checkResult() {
		if (first.isScoreTen()) {
			addSecondScore(Score.ofScore(ScoreBound.MINIMUM_SCORE_BOUND.getBound()));
		}
		if (Objects.isNull(second)) {
			throw new IllegalStateException("scores does not have second score result!");
		}
		return Result.findByScores(first, second);
	}

	public boolean canPlayMore() {
		return Objects.isNull(first) || (! first.isScoreTen() && Objects.isNull(second));
	}
}
