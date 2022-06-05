package bowling.state;

import java.util.Objects;

import bowling.point.Point;
import bowling.score.Score;

public class Remain implements State {

	private final Point first;

	public Remain(int value) {
		this(Point.of(value));
	}

	public Remain(Point first) {
		if (first == Point.max()) {
			throw new IllegalArgumentException("핀이_남은_상태는 최댓값의 점수를 가질 수 없습니다.");
		}
		this.first = first;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public State throwBowl(int throwCount) {
		Point operand = Point.of(throwCount);
		if (Spare.isConstructible(first, operand)) {
			return new Spare(first, operand);
		}
		return new Open(this.first, operand);
	}

	@Override
	public String symbol() {
		return first.symbol();
	}

	@Override
	public Score score() {
		return Score.remain(first.score());
	}

	@Override
	public Score bonus(Score prevScore) {
		return prevScore.accumulate(this.first.score());
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
