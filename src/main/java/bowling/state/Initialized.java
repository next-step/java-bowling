package bowling.state;

import bowling.point.Point;

public class Initialized implements State {

	public static final Initialized CACHE = new Initialized();

	private Initialized() {
	}

	public static Initialized getInstance() {
		return CACHE;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public State throwBowl(int throwCount) {
		Point point = Point.of(throwCount);
		if (Strike.isConstructible(point)) {
			return Strike.getInstance();
		}
		return new Remain(throwCount);
	}

	@Override
	public String symbol() {
		return "";
	}
}
