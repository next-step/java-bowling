package domain.state;

public class InProgress implements State {

	private int bowlingPins;

	private InProgress(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static InProgress of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("InProgress에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new InProgress(bowlingPins);
	}

}
