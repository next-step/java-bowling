package bowlinggame.domain.result;

import bowlinggame.domain.frame.Pin;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Miss implements Result {

	private static Map<Integer, Miss> cache = new HashMap<>();

	private final Integer pinCount;

	private Miss(Integer pinCount) {
		this.pinCount = pinCount;
	}

	public static Miss getInstance(Integer pinCount) {
		if (pinCount >= Pin.MAX_PIN_COUNT) {
			throw new IllegalArgumentException("잘못된 핀개수입니다.");
		}
		return cache.computeIfAbsent(pinCount, key -> new Miss(key));
	}

	@Override
	public Result next(Pin pin) {
		if (pin.sum(pinCount) == Pin.MAX_PIN_COUNT) {
			return Spare.getInstance();
		}
		if (pin.isStandAll()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getPinCount());
	}

	@Override
	public int getKnockDownPinCount() {
		return pinCount;
	}

	@Override
	public String getResult() {
		return pinCount.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Miss)) {
			return false;
		}
		Miss miss = (Miss) o;
		return Objects.equals(pinCount, miss.pinCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pinCount);
	}
}
