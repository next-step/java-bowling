package bowlinggame.domain.frame;

public class FrameResult {

	public static final int UNSCORE = -1;

	private String result;
	private int score;

	public FrameResult(String result, int score) {
		this.result = result;
		this.score = score;
	}

	public int calculateScore(int beforeScore) {
		if (isUnscore()) {
			return beforeScore;
		}
		score += beforeScore;
		return score;
	}

	public boolean isUnscore() {
		return score == UNSCORE;
	}

	public String getRollResults() {
		return result;
	}

	public String getScore() {
		if (isUnscore()) {
			return Score.EMPTY_SCORE_CHARACTER;
		}
		return String.valueOf(score);
	}
}
