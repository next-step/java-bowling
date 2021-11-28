package bowling.domain;

import bowling.exception.IndexRangeException;

public class Index {
	public static final int MIN_OF_INDEX = 1;
	public static final int MAX_OF_INDEX = 10;

	private final int value;

	private Index(int value) {
		validateRange(value);
		this.value = value;
	}

	private void validateRange(int value) {
		if (value < MIN_OF_INDEX || value > MAX_OF_INDEX) {
			throw new IndexRangeException();
		}
	}

	public static Index create(int value) {
		return new Index(value);
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Index index = (Index)obj;

		return value == index.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
