package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;
import java.util.HashMap;
import java.util.Map;

public class Miss implements Result {

	private static final Map<Integer, Miss> CACHE = new HashMap<>();

	private Integer pinCount;

	private Miss(Integer pinCount) {
		this.pinCount = pinCount;
	}

	public static Miss getInstance(int pinCount) {
		if (pinCount >= 10) {
			throw new IllegalArgumentException("잘못된 핀개수입니다.");
		}
		return CACHE.computeIfAbsent(Integer.valueOf(pinCount), key -> new Miss(key));
	}

	@Override
	public Result next(Pin pin) {
		if (pin.isAllKnockedDown()) {
			return Spare.getInstance(this);
		}
		if (pin.isSameKnockedPinCount(pinCount)) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getKnockedPinCount() - pinCount);
	}

	@Override
	public int getKnockedDownPinCount() {
		return pinCount.intValue();
	}

	@Override
	public int getBonusCount() {
		return 0;
	}

	@Override
	public String getResult() {
		return pinCount.toString();
	}
}
