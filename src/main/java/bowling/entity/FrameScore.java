package bowling.entity;

public class FrameScore {
	private final int score;
	private final int remainder;

	public FrameScore(int score, int remainder) {
		this.score = score;
		this.remainder = remainder;
	}

	public boolean isShow() {
		return remainder == 0;
	}

	public int getScore() {
		return score;
	}

	public static FrameScore of(Frame frame) {
		return new FrameScore(frame.getScore(), frame.getRemainderCount());
	}

	public FrameScore appendScore(int score) {
		if (remainder == 0) {
			return this;
		}
		return new FrameScore(score + this.score, remainder - 1);
	}

	@Override
	public String toString() {
		return "score= " + score + " remainder= " + remainder + " isShow= " + isShow();
	}
}
