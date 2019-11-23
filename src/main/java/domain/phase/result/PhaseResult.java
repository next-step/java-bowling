package domain.phase.result;

public interface PhaseResult {

	boolean hasNextPhase(PhaseResult prevResult);

	boolean shouldRestoreBowlingPins();

	String toSign(int fallenBowlingPins);

}
