package bowling.entity;

public class NormalFrame {
	private final int no;
	private final NormalFrameScore frameScore;

	public NormalFrame(int no, NormalFrameScore frameScore) {
		this.no = no;
		this.frameScore = frameScore;
	}

	public String getScoreString() {
		return this.frameScore.getFrameScoreToString();
	}

	public static NormalFrame ofNext(int index, int score, NormalFrame normalFrame) {
		if (normalFrame == null) {
			return new NormalFrame(index, NormalFrameScore.ofFirst(score));
		}
		return new NormalFrame(index, NormalFrameScore.ofSecond(normalFrame.frameScore, score));
	}

	public boolean isKeepGoing() {
		return frameScore.isKeepGoing();
	}

	public int getNo() {
		return no;
	}
}
