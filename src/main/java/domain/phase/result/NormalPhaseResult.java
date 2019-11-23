package domain.phase.result;

public enum NormalPhaseResult implements PhaseResult {

	IN_PROGRESS("%s"),
	STRIKE("X"),
	SPARE("/"),
	MISS("%s"),
	GUTTER("-");

	private final String format;

	NormalPhaseResult(String format) {
		this.format = format;
	}

	@Override
	public boolean hasNextPhase(PhaseResult prevResult) {
		return this == IN_PROGRESS;
	}

	@Override
	public boolean shouldRestoreBowlingPins() {
		return false;
	}

	@Override
	public String toSign(int fallenBowlingPins) {
		return String.format(format, fallenBowlingPins);
	}

}
