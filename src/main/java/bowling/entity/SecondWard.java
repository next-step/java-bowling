package bowling.entity;

public class SecondWard extends Ward {
	public SecondWard(int score) {
		super(score);
	}

	@Override
	boolean hasNext() {
		return false;
	}
}
