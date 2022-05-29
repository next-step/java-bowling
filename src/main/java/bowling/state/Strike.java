package bowling.state;

import bowling.point.Point;

public class Strike implements State {

	private static final Strike CACHE = new Strike();
	private static final Point STRIKE_POINT = Point.max();

	private Strike() {
	}

	public static Strike of() {
		return CACHE;
	}

	public static boolean isConstructible(Point point) {
		return point == STRIKE_POINT;
	}

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public State throwBowl(int throwCount) {
		throw new UnsupportedOperationException("투구를 할 수 없는 상태입니다.");
	}
}
