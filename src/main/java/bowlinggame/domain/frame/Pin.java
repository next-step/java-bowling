package bowlinggame.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class Pin {

	public static final int MIN_PIN_COUNT = 0;
	public static final int MAX_PIN_COUNT = 10;
	private static Map<Integer, Pin> cache = new HashMap<>();

	private Integer pinCount;

	private Pin(Integer pinCount) {
		this.pinCount = pinCount;
	}

	public static Pin from(Integer pinCount) {
		if (pinCount > MAX_PIN_COUNT|| pinCount < MIN_PIN_COUNT) {
			throw new IllegalArgumentException("잘못된 투구입니다.");
		}

		return cache.computeIfAbsent(pinCount, key -> new Pin(key));
	}

	public int sum(int pinCount) {
		return Integer.sum(this.pinCount, pinCount);
	}

	public boolean canRecord(Pin remainingPin) {
		return this.pinCount <= remainingPin.pinCount;
	}

	public boolean isKnockedDownAll() {
		return pinCount == MAX_PIN_COUNT;
	}

	public boolean isStandAll() {
		return pinCount == MIN_PIN_COUNT;
	}

	public int getPinCount() {
		return pinCount;
	}
}
