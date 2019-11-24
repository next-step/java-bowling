package domain.state;

public class Spare implements State {

	private int bowlingPins;

	private Spare(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Spare of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("Spare에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new Spare(bowlingPins);
	}

}
