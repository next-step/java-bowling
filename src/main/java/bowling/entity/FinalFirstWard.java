package bowling.entity;

public class FinalFirstWard extends Ward {
	public FinalFirstWard(int score) {
		super(score);
	}

	@Override
	boolean hasNext() {
		return true;
	}
}
