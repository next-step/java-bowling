package domain.state;

public class Miss implements State {

	private int bowlingPins;

	private Miss(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Miss of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("Miss에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new Miss(bowlingPins);
	}

}
