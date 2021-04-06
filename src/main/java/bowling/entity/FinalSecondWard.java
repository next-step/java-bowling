package bowling.entity;

public class FinalSecondWard extends Ward {
	private final int prevScore;

	public FinalSecondWard(int score, int prevScore) {
		super(score);
		this.prevScore = prevScore;
	}

	@Override
	boolean hasNext() {
		return prevScore == 10 || super.getScore() == 10 || prevScore + super.getScore() == 10;
	}
}
