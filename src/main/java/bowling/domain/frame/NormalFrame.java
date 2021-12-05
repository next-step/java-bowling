package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.Index;
import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.CannotCalculateException;

public class NormalFrame implements Frame {
	private final Index index;
	private State state;
	private Frame next;

	private NormalFrame(Index index, State state) {
		this.index = index;
		this.state = state;
	}

	public static NormalFrame create(Index index, State state) {
		return new NormalFrame(index, state);
	}

	public static NormalFrame create(Index index) {
		return create(index, Ready.create());
	}

	public static NormalFrame initialize() {
		return create(Index.first());
	}

	@Override
	public Frame bowl(Pins pins) {
		this.state = state.bowl(pins);

		if (state.isEnd()) {
			return createNextFrame();
		}
		return this;
	}

	private Frame createNextFrame() {
		if (index.next().isMax()) {
			return createLastFrameAndSetNext();
		}

		return createNormalFrameAndSetNext();
	}

	private LastFrame createLastFrameAndSetNext() {
		LastFrame next = LastFrame.initialize();
		this.next = next;
		return next;
	}

	private NormalFrame createNormalFrameAndSetNext() {
		NormalFrame next = create(index.next());
		this.next = next;
		return next;
	}

	@Override
	public boolean isEnd() {
		return state.isEnd();
	}

	public int getFrameIndex() {
		return index.getValue();
	}

	@Override
	public String symbol() {
		return state.symbol();
	}

	@Override
	public int score() {
		if (!isEnd()) {
			return Score.INCALCULABLE_SCORE;
		}

		Score score = state.score();
		if (score.canCalculateScore()) {
			return score.getScore();
		}
		return next.calculateAdditionalScore(score);
	}

	@Override
	public int calculateAdditionalScore(Score prevScore) {
		try {
			return catchCalculateAdditionalScore(prevScore);
		} catch (CannotCalculateException e) {
			return Score.INCALCULABLE_SCORE;
		}
	}

	private int catchCalculateAdditionalScore(Score prevScore) {
		Score score = state.calculateAdditionalScore(prevScore);
		if (score.canCalculateScore()) {
			return score.getScore();
		}
		if (next == null) {
			throw new CannotCalculateException();
		}
		return next.calculateAdditionalScore(score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		NormalFrame frame = (NormalFrame)obj;

		return Objects.equals(index, frame.index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(index);
	}
}
