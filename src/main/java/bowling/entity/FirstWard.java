package bowling.entity;

public class FirstWard extends Ward {
	public FirstWard(int score) {
		super(score);
	}

	@Override
	public boolean hasNext() {
		return super.getScore() < 10;
	}
}
