package bowling.domain.frame;

public class FinalFrame extends Frame {

	public FinalFrame save(int score) {
		this.add(score);

		if (isNext() || isMaxSize()) {
			return new FinalFrame();
		}
		return this;
	}

	public boolean isNext() {
		return isMiss(this.getLastIndex()) && this.getScores().size() > 1;
	}

	public boolean isMaxSize() {
		return this.getScores().size() == 3;
	}
}
