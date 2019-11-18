package domain.frame.result;

import domain.phase.result.PhaseResult;

import java.util.*;

public class FrameResult {

	private final List<PhaseResult> phaseResults = new ArrayList<>();
	private final List<Integer> fallenBowlingPins = new ArrayList<>();

	public FrameResult() {

	}

	public FrameResult(PhaseResult result, int fallenBowlingPins) {
		this.phaseResults.add(result);
		this.fallenBowlingPins.add(fallenBowlingPins);
	}

	public FrameResult(List<PhaseResult> results, List<Integer> fallenBowlingPinsList) {
		this.phaseResults.addAll(results);
		this.fallenBowlingPins.addAll(fallenBowlingPinsList);
	}

	public void saveResult(PhaseResult result, int fallenBowlingPins) {
		this.phaseResults.add(result);
		this.fallenBowlingPins.add(fallenBowlingPins);
	}

	public PhaseResult getPrevResult() {
		return phaseResults.stream()
				.min(Collections.reverseOrder())
				.orElse(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FrameResult that = (FrameResult) o;
		return Objects.equals(phaseResults, that.phaseResults) &&
				Objects.equals(fallenBowlingPins, that.fallenBowlingPins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(phaseResults, fallenBowlingPins);
	}

}
