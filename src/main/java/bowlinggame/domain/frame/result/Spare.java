package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Spare implements Result {

	public static final String RESULT_CHARACTER = "/";
	private static final Map<Result, Spare> CACHE = new HashMap<>();

	private Result firstResult;

	private Spare(Result firstResult) {
		this.firstResult = firstResult;
	}

	public static Spare getInstance(Result firstResult) {
		return CACHE.computeIfAbsent(firstResult, key -> new Spare(key));
	}

	@Override
	public Result next(Pin pin) {
		if (pin.isAllKnockedDown()) {
			return Strike.getInstance();
		}
		if (pin.isAllStanding()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getKnockedPinCount());
	}

	@Override
	public int getKnockedDownPinCount() {
		return Pin.MAX_COUNT - firstResult.getKnockedDownPinCount();
	}

	@Override
	public int getBonusCount() {
		return 1;
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Spare)) {
			return false;
		}
		Spare spare = (Spare) o;
		return Objects.equals(firstResult, spare.firstResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstResult);
	}
}
