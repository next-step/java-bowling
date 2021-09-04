package bowling.view.dto;

import bowling.domain.frame.Frame;
import bowling.view.ResultFlag;
import java.util.List;

public class PrintDto {

	private static final String DELIMITER = "|";

	private final String string;

	private PrintDto(final String string) {
		this.string = string;
	}

	public static PrintDto of() {
		return new PrintDto("      ");
	}

	public static PrintDto forFrameResult(final Frame frame) {
		final String result = ResultFlag.generateResultByFrame(frame, DELIMITER);
		return new PrintDto(String.format("%5s ", result));
	}

	public static PrintDto forScore(final Frame frame, final List<Frame> frames) {
		final String result = String.format("%5s ", frame.caculateScore(frames).getValue());
		return new PrintDto(result);
	}

	@Override public String toString() {
		return string + DELIMITER;
	}
}
