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
		if (phaseResults.isEmpty()) {
			return null;
		}
		return phaseResults.get(phaseResults.size() - 1);
	}

	// TODO: 2019-11-18 어딘가로 로직을 이동
	// TODO: 2019-11-18 문자와 숫자를 적절히 출력.. 어떻게?
	public String getPhaseResults() {
		if (phaseResults.isEmpty()) {
			return "   ";
		}
		if (phaseResults.size() == 1) {
			return String.format("%s  ", phaseResults.get(0).toSign(fallenBowlingPins.get(0)));
		}
		return String.format("%s|%s", phaseResults.get(0).toSign(fallenBowlingPins.get(0)),
				phaseResults.get(1).toSign(fallenBowlingPins.get(1)));
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
