package domain.state;

public class Strike implements State {

	private int bowlingPins;

	private Strike(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Strike of(int bowlingPins) {
		if (bowlingPins != 10) {
			throw new IllegalArgumentException("Strike는 10개를 쓰러뜨린 경우입니다");
		}
		return new Strike(bowlingPins);
	}

}
