package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Results {

	private List<Result> results = new ArrayList<>();

	public Results() {}

	public Results(List<Result> results) {
		this.results = results;
	}

	public Result record(Pin pin) {
		Result result = getNextResult(pin);
		results.add(result);
		return result;
	}

	private Result getNextResult(Pin pin) {
		Optional<Result> current = getCurrentResult();
		if (!current.isPresent()) {
			return Result.first(pin);
		}

		return current.get().next(pin);
	}

	public Optional<Result> getCurrentResult() {
		if (results.isEmpty()) {
			return Optional.empty();
		}

		int lastIndex = results.size() - 1;
		return Optional.of(results.get(lastIndex));
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
		List<String> displayResults = results.stream()
				.map(Result::getResult)
				.collect(Collectors.toList());
		return Collections.unmodifiableList(displayResults);
	}

	public Score addScore(Score score) {
		results.stream()
				.forEach(result -> score.calculateBonus(result));
		return score;
	}
}
