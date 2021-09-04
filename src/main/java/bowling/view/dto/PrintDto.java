package bowling.view.dto;

import bowling.domain.frame.Frame;
import bowling.view.ResultFlag;

public class PrintDto {

	private static final String DELIMITER = "|";

	private final String string;

	private PrintDto(final String input) {
		this.string = input;
	}

	private PrintDto(final int input) {
		this.string = String.valueOf(input);
	}

	public static PrintDto of() {
		return new PrintDto("     ");
	}

	public static PrintDto forFrameResult(final Frame frame) {
		return new PrintDto(ResultFlag.generateResultByFrame(frame, DELIMITER));
	}

	public static PrintDto forScore(final int score) {
		return new PrintDto(score);
	}

	@Override public String toString() {
		return String.format("%5s ", string) + DELIMITER;
	}
}
