package bowling.domain;

public enum PitchType {

	STRIKE("x"),
	SPARE("/"),
	MISS(""),
	GUTTER("-")
	;

	private final String sign;

	PitchType(String sign) {
		this.sign = sign;
	}

	public static String getSign(PitchType pitchType) {
		return pitchType.sign;
	}
}
