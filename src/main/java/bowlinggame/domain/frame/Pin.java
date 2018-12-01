package bowlinggame.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class Pin {

	private static final Map<Integer, Pin> CACHE = new HashMap<>();

	public static final int MIN_COUNT = 0;
	public static final int MAX_COUNT = 10;

	private int count;

	private Pin(int count) {
		this.count = count;
	}

	public static Pin ready() {
		return Pin.of(MAX_COUNT);
	}

	public static Pin fromKnockedPinCount(int knockedDownPinCount) {
		int remainingPinCount = MAX_COUNT - knockedDownPinCount;
		return Pin.of(remainingPinCount);
	}

	public static Pin of(int pinCount) {
		if (pinCount < MIN_COUNT || pinCount > MAX_COUNT) {
			throw new IllegalArgumentException("잘못된 핀개수입니다.");
		}

		return CACHE.computeIfAbsent(Integer.valueOf(pinCount), key -> new Pin(key));
	}

	public Pin knockDown(int pinCount) {
		if (count < pinCount) {
			throw new IllegalArgumentException("넘어뜨린 핀이 남은 핀보다 많습니다.");
		}
		return Pin.of(count - pinCount);
	}

	public boolean isAllKnockedDown() {
		return count == MIN_COUNT;
	}

	public boolean isAllStanding() {
		return count == MAX_COUNT;
	}

	public int getKnockedPinCount() {
		return MAX_COUNT - count;
	}

	public boolean isSameKnockedPinCount(int pinCount) {
		return getKnockedPinCount() == pinCount;
	}
}
