package bowling.state;

import bowling.point.Point;

public class Strike implements State {

	private static final Point POINT = Point.max();
	public static final Strike INSTANCE = new Strike();

	private Strike() {
	}

	public static boolean isConstructible(int throwCount) {
		return Point.of(throwCount) == POINT;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public State throwBowl(int throwCount) {
		return null;
	}
}
