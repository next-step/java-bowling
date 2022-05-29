package bowling.state;

import java.util.Objects;

import bowling.point.Point;

public class Open implements State {

	private final Point first;
	private final Point second;

	public Open(int first, int second) {
		this(Point.of(first), Point.of(second));
	}

	public Open(Point first, Point second) {
		if (!isConstructible(first, second)) {
			throw new IllegalArgumentException("오픈이 되려면 두 시도의 합이 최댓값보다 작아야합니다.");
		}
		this.first = first;
		this.second = second;
	}

	private boolean isConstructible(Point first, Point second) {
		Point sum = first.add(second);
		return sum.isLessThan(Point.max());
	}

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public State throwBowl(int throwCount) {
		throw new UnsupportedOperationException("투구를 할 수 없는 상태입니다.");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Open open = (Open)o;
		return Objects.equals(first, open.first) && Objects.equals(second, open.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
