package bowlinggame.domain.frame;

import bowlinggame.domain.frame.result.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameResult {

	private List<Result> results = new ArrayList<>();

	public FrameResult() {}

	public FrameResult(List<Result> results) {
		this.results = results;
	}

	public Result record(Pin pin) {
		Result result = getNextResult(pin);
		results.add(result);
		return result;
	}

	private Result getNextResult(Pin pin) {
		if (results.isEmpty()) {
			return Result.first(pin);
		}

		int lastIndex = results.size() - 1;
		return results.get(lastIndex).next(pin);
	}

	public int getTotalKnockedDownPinCount() {
		return results.stream()
				.mapToInt(Result::getKnockedDownPinCount)
				.sum();
	}

	public boolean isSameRollOpportunity(int rollOpportunity) {
		return results.size() == rollOpportunity;
	}

	public boolean isAllKnockedDown() {
		return getTotalKnockedDownPinCount() >= Pin.MAX_COUNT;
	}

	public List<String> getDisplayResults() {
		return results.stream()
				.map(Result::getResult)
				.collect(Collectors.toList());
	}
}
