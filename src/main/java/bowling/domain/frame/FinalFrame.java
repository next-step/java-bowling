package bowling.domain.frame;

import static bowling.util.FrameSize.*;

import java.util.Optional;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.util.ResultUtil;

public class FinalFrame implements Frame {

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
	public void playFrame(Score score) {
		scores.playFrame(score, this);
	}

	@Override
	public Frame addNextFrame() {
		throw new IllegalArgumentException("마지막 프레임은 다음 프레임을 만들지 않습니다.");
	}

	@Override
	public boolean canPlayMore() {
		if (!scores.getFirst().isPresent() || !scores.getSecond().isPresent()) {
			return true;
		}
		return !scores.getBonus().isPresent() && ResultUtil.BONUS_SCORE_RESULT.contains(scores.checkResult());
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
	public Optional<Score> calculateFrameTotalScore() {
		if (!scores.hasCheckResult()) {
			return Optional.empty();
		}
		return Optional.ofNullable(scores.calculateFrameTotalScore());
	}
}
