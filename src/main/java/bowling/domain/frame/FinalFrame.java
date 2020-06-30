package bowling.domain.frame;

import static bowling.util.FrameSize.*;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

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

	public void addFirstScore(Score firstScore) {
		scores.addFirstScore(firstScore);
	}

	public void addSecondScore(Score second) {
		scores.addSecondScore(second);
	}

	@Override
	public Frame addNextFrame() {
		throw new IllegalArgumentException("마지막 프레임은 다음 프레임을 만들지 않습니다.");
	}

	@Override
	public boolean canPlayMore() {
		return scores.canPlayMore();
	}

	@Override
	public int getIndex() {
		return index;
	}

	public Scores getScores() {
		return scores;
	}
}
