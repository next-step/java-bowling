package bowling.domain;

public class Pins {
	private static final int MIN_PIN = 0;
	private static final int MAX_PIN = 10;
	private static final String MIN_PIN_ERROR_MSG = "쓰러트릴 핀 갯수는 0개 이하일 수 없습니다.";
	private static final String MAX_PIN_ERROR_MSG = "쓰러트릴 핀 갯수는 총 10개를 넘을 수 없습니다.";

	private final PinStatus pinStatus;
	private final int frameNumber;

	public Pins(int frameNumber) {
		this.pinStatus = new PinStatus();
		this.frameNumber = frameNumber;
	}

	public void play(int hittingNumber) {
		validateHittingNumber(hittingNumber);
		pinStatus.add(hittingNumber);
	}

	public PinStatus pinStatus() {
		return pinStatus;
	}

	private void validateHittingNumber(int hittingNumber) {
		if (hittingNumber < MIN_PIN) {
			throw new IllegalArgumentException(MIN_PIN_ERROR_MSG);
		}
		int firstPin = pinStatus.totalPin();
		int maxPin = firstPin + hittingNumber;
		if (frameNumber != 10 && maxPin > MAX_PIN) {
			throw new IllegalArgumentException(MAX_PIN_ERROR_MSG);
		}
		if (frameNumber == 10 && hittingNumber > MAX_PIN) {
			throw new IllegalArgumentException(MAX_PIN_ERROR_MSG);
		}
	}
}
