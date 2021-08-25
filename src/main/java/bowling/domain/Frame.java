package bowling.domain;

public abstract class Frame {

	protected static final String DELIMITER = "|";

	protected Pitch first;
	protected Pitch second;

	protected Frame() {

	}

	protected Frame(final Pitch first) {
		this.first = first;
	}

	protected Frame(final Pitch first, final Pitch second) {
		this.first = first;
		this.second = second;
	}

	public abstract boolean isEnd();

	public abstract void pitch(final int pinNumber);

	public abstract String getResult();
}
