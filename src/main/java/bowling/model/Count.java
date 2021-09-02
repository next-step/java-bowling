package bowling.model;

import java.util.Objects;

public class Count {

	private static final int LIMIT_START = 1;
	private static final int LIMIT_END = 4;
	private static final String LIMIT_RANGE_ERROR_MESSAGE = "카운트는 0보다 크고 4보다 작거나 같습니다.";
	
	private final int count;

	public Count(int count) {
		checkCountRange(count);
		this.count = count;
	}

	private void checkCountRange(int count) {
		if (count < LIMIT_START || count > LIMIT_END) {
			throw new IllegalArgumentException(LIMIT_RANGE_ERROR_MESSAGE);
		}
	}

	public int getCount() {
		return count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Count count1 = (Count)o;
		return count == count1.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
}
