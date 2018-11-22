package bowlinggame.domain.frame;

import bowlinggame.domain.result.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FrameResult {

	private List<Result> results = new ArrayList<>();

	public Result record(Pin pin) {
		Result result = getPinResult(pin);
		results.add(result);
		return result;
	}

	private Result getPinResult(Pin pin) {
		Optional<Result> lastResult = getLastResult();
		if (lastResult.isPresent()) {
			return lastResult.get().next(pin);
		}
		return Result.first(pin);
	}

	public Pin getRemainingPin() {
		int remainingPinCount = Pin.MAX_PIN_COUNT;
		if (getLastResult().isPresent()) {
			Result result = getLastResult().get();
			remainingPinCount -= result.getKnockDownPinCount();
		}
		return Pin.from(remainingPinCount);
	}

	public boolean isSameRollOpportunity(int rollOpportunity) {
		return results.size() == rollOpportunity;
	}

	public boolean isSameLastResult(Result result) {
		Optional<Result> lastResult = getLastResult();
		if (!lastResult.isPresent()) {
			return false;
		}
		return lastResult.get().equals(result);
	}

	private Optional<Result> getLastResult() {
		if (results.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(results.get(results.size() - 1));
	}

	public List<Result> getTotalResult() {
		return Collections.unmodifiableList(results);
	}
}
