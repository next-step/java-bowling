package bowling.domain.frame;

import static bowling.util.FrameSize.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import bowling.domain.result.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class FinalFrame implements Frame {

	private static final List<Result> BONUS_SCORE_RESULT = Arrays.asList(Result.STRIKE, Result.SPARE);
	private final int index;
	private final Scores scores;

	private FinalFrame(Scores scores) {
		this.index = FRAME_SIZE;
		this.scores = scores;
	}

	public static FinalFrame of() {
		return new FinalFrame(Scores.of());
	}

	@Override
	public void addScore(Score score) {
		if (scores.isFirstScoreNull()) {
			addFirstScore(score);
			return;
		}
		if (scores.isSecondScoreNull()) {
			addSecondScore(score);
			return;
		}
		if (scores.isBonusScoreNull()) {
			addBonusScore(score);
		}
	}

	@Override
	public void addFirstScore(Score firstScore) {
		scores.addFirstScore(firstScore);
	}

	@Override
	public void addSecondScore(Score second) {
		scores.addSecondScore(second);
	}

	@Override
	public void addBonusScore(Score bonus) {
		scores.addBonusScore(bonus);
	}

	@Override
	public Frame addNextFrame() {
		throw new IllegalArgumentException("마지막 프레임은 다음 프레임을 만들지 않습니다.");
	}

	@Override
	public boolean canPlayMore() {
		if (Objects.isNull(scores.getFirst()) || Objects.isNull(scores.getSecond())) {
			return true;
		}
		return Objects.isNull(scores.getBonus()) && BONUS_SCORE_RESULT.contains(scores.checkResult());
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public Scores getScores() {
		return scores;
	}

	@Override
	public Score calculateFrameTotalScore() {
		return scores.calculateFrameTotalScore();
	}
}
