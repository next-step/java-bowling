package bowling.entity;

public class FinalThirdWard extends Ward{
	public FinalThirdWard(int score) {
		super(score);
	}

	@Override
	boolean hasNext() {
		return false;
	}
}
