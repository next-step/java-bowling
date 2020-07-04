package bowling.domain.score;

import java.util.Objects;
import java.util.Optional;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.result.Result;
import bowling.util.ResultUtil;
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

	public boolean isFirstScoreNull() {
		return Objects.isNull(first);
	}

	public boolean isSecondScoreNull() {
		return Objects.isNull(second);
	}

	public boolean isBonusScoreNull() {
		return Objects.isNull(bonus);
	}

	public void playFrame(Score score, Frame frame) {
		if (isFirstScoreNull()) {
			playFirstHalf(score);
			return;
		}
		if (isSecondScoreNull()) {
			playSecondHalf(score);
			return;
		}
		if (isBonusScoreNull() && frame instanceof FinalFrame) {
			playBonusHalf(score);
		}
	}

	public void playFirstHalf(Score firstScore) {
		if (Objects.nonNull(first)) {
			throw new IllegalArgumentException("이미 첫 번째 타구의 점수가 있습니다.");
		}
		this.first = firstScore;
	}

	public void playSecondHalf(Score second) {
		this.second = second;
		if (first.getScore() + second.getScore() > ScoreBound.MAXIMUM_SCORE_BOUND.getBound()) {
			hasCheckResultAndVerifyScoreSumCanExceedTen();
		}
	}

	public void playBonusHalf(Score bonus) {
		first.add(bonus);
		this.bonus = bonus;
	}

	private void hasCheckResultAndVerifyScoreSumCanExceedTen() {
		if (hasCheckResult() && !ResultUtil.BONUS_SCORE_RESULT.contains(checkResult())) {
			throw new IllegalArgumentException("first and second frames score have more than 10 scores. wrong score.");
		}
	}

	public Optional<Score> getFirst() {
		return Optional.ofNullable(first);
	}

	public Optional<Score> getSecond() {
		return Optional.ofNullable(second);
	}

	public Optional<Score> getBonus() {
		return Optional.ofNullable(bonus);
	}

	public Result checkResult() {
		if (first.isScoreTen()) {
			playSecondHalf(Score.of(ScoreBound.MINIMUM_SCORE_BOUND.getBound()));
		}
		if (Objects.isNull(second)) {
			throw new IllegalStateException("scores does not have second score result!");
		}
		return ResultUtil.findByScores(first, second);
	}

	public boolean canPlayMore() {
		return Objects.isNull(first) || (!first.isScoreTen() && Objects.isNull(second));
	}

	public Score calculateFrameTotalScore() {
		if (Objects.isNull(first)) {
			return Score.of(0);
		}
		if (Objects.isNull(second)) {
			return first;
		}
		if (Objects.isNull(bonus)) {
			return first.add(second);
		}
		return first.add(second).add(bonus);
	}

	public boolean hasCheckResult() {
		return Objects.nonNull(second);
	}
}
