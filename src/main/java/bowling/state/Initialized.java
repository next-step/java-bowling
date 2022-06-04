package bowling.state;

import bowling.point.Point;
import bowling.score.Score;

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

	@Override
	public Score score() {
		throw new UnsupportedOperationException("초기화 상태는 점수를 계산할 수 없습니다.");
	}

	@Override
	public Score bonus(Score prevScore) {
		throw new UnsupportedOperationException("초기화 상태는 보너스 점수를 계산할 수 없습니다.");
	}
}
