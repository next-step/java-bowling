package bowling.domain;

import bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DownedPinCounts {

	static final int MAX_PIN_COUNT = 10;
	static final String OVER_MAX_PIN_COUNT = "쓰러뜨린 핀의 갯수는 %d개를 초과할 수 없습니다.";

	private final List<DownedPinCount> downedPinCounts;

	public DownedPinCounts() {
		this.downedPinCounts = new ArrayList<>();
	}

	public DownedPinCounts(DownedPinCount downedPinCount) {
		List<DownedPinCount> downedPinCounts = new ArrayList<>();
		downedPinCounts.add(downedPinCount);
		this.downedPinCounts = downedPinCounts;
	}

	public boolean add(DownedPinCount downedPinCount) {
		validatePinScoreSum(downedPinCount);
		return downedPinCounts.add(downedPinCount);
	}

	public Integer getDownedPinCountSum() {
		return downedPinCounts.stream()
				.map(DownedPinCount::intValue)
				.reduce(0, Integer::sum);
	}

	public int size() {
		return this.downedPinCounts.size();
	}

	public boolean isAllPinDowned() {
		return (getDownedPinCountSum() == MAX_PIN_COUNT);
	}

	private void validatePinScoreSum(DownedPinCount downedPinCount) {
		if(isValidPinCountSumPerFrame(downedPinCount)) {
			throw new BowlingException(String.format(OVER_MAX_PIN_COUNT, MAX_PIN_COUNT));
		}
	}
	private boolean isValidPinCountSumPerFrame(DownedPinCount downedPinCount) {
		return isAllPinDowned() || (MAX_PIN_COUNT - getDownedPinCountSum() < downedPinCount.intValue());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DownedPinCounts that = (DownedPinCounts) o;
		return downedPinCounts.equals(that.downedPinCounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(downedPinCounts);
	}
}
