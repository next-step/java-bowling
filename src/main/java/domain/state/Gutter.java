package domain.state;

public class Gutter implements State {

	private int bowlingPins;

	private Gutter(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Gutter of(int bowlingPins) {
		if (bowlingPins != 0) {
			throw new IllegalArgumentException("GUTTER는 0개를 쓰러뜨린 경우 입니다");
		}
		return new Gutter(bowlingPins);
	}

}
