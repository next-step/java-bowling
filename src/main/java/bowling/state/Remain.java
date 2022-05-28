package bowling.state;

import java.util.Objects;

import bowling.point.Point;

public class Remain implements State {

	private final Point first;

	public Remain(int first) {
		this.first = Point.of(first);
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public State throwBowl(int throwCount) {
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Remain remain = (Remain)o;
		return Objects.equals(first, remain.first);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first);
	}
}
