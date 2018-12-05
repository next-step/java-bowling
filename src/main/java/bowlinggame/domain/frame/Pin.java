package bowlinggame.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class Pin {

	public static final String GUTTER_RESULT_CHARACTER = "-";
	private static final Map<Integer, Pin> CACHE = new HashMap<>();

	public static final int MIN_COUNT = 0;
	public static final int MAX_COUNT = 10;

	private int count;

	private Pin(int count) {
		this.count = count;
	}

	public static Pin ZERO() {
		return Pin.of(MIN_COUNT);
	}

	public static Pin TEN() {
		return Pin.of(MAX_COUNT);
	}

	public static Pin of(int pinCount) {
		if (pinCount < MIN_COUNT || pinCount > MAX_COUNT) {
			throw new IllegalArgumentException("잘못된 핀개수입니다.");
		}

		return CACHE.computeIfAbsent(Integer.valueOf(pinCount), key -> new Pin(key));
	}

	public String getResult() {
		if (isAllStanding()) {
			return GUTTER_RESULT_CHARACTER;
		}
		return String.valueOf(count);
	}

	public boolean isAllKnockedDown(Pin pin) {
		Pin total = add(pin);
		return total.isAllKnockedDown();
	}

	public Pin add(Pin pin) {
		return add(pin.count);
	}

	public Pin add(int pinCount) {
		int totalPin = count + pinCount;
		if (totalPin > Pin.MAX_COUNT) {
			throw new IllegalArgumentException("넘어뜨린 핀이 남은 핀보다 많습니다.");
		}
		return Pin.of(totalPin);
	}

	public boolean isAllKnockedDown() {
		return count == MAX_COUNT;
	}

	public boolean isAllStanding() {
		return count == MIN_COUNT;
	}

	public Score toScore() {
		return toScore(0);
	}

	public Score toScore(int bonusCount) {
		return Score.of(count, bonusCount);
	}
}
