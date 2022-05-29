package bowling.state;

import java.util.Objects;

import bowling.point.Point;

public class Spare implements State {

	private final Point first;
	private final Point second;

	public Spare(int first, int second) {
		this(Point.of(first), Point.of(second));
	}

	public Spare(Point first, Point second) {
		if (!isConstructible(first, second)) {
			throw new IllegalArgumentException("스페가 되려면 두 시도의 합이 최댓값이어야 합니다.");
		}
		this.first = first;
		this.second = second;
	}

	public static boolean isConstructible(Point remain, Point operand) {
		return remain.add(operand) == Point.max();
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
		Spare spare = (Spare)o;
		return Objects.equals(first, spare.first) && Objects.equals(second, spare.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
