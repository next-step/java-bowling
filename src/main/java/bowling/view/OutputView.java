package bowling.view;

import bowling.domain.game.Game;

import java.util.stream.Collectors;

public class OutputView {
	private static final String AREA_PER_FRAME = "      ";
	private static final String FRAME_EDGE = "|";
	private static final String NAME_HEADER = "NAME";
	private static final int DOUBLE_DIGIT_START_VALUE = 10;
	private static final String DOUBLE_DIGIT_FORMAT_PREFIX = "0";

	public static void showInitializedGame(Game game) {
		System.out.println(getHeaderAreaText(game));
		System.out.println(getValueAreaText(game));
	}

	private static String getHeaderAreaText(Game game) {
		return buildStartFrame(NAME_HEADER) + getHeaderTitlePerFrame(game);
	}
	private static String getValueAreaText(Game game) {
		return buildStartFrame(game.getPlayersName()) + getValueTextPerFrame(game);
	}

	private static String getHeaderTitlePerFrame(Game game) {
		return game.getFrames()
				.stream()
				.map(frame -> buildNoneStartFrame(getFormattedSequence(frame.getFrameSequence())))
				.collect(Collectors.joining());
	}

	private static String getValueTextPerFrame(Game game) {
		return game.getFrames()
				.stream()
				.map(frame -> buildNoneStartFrame(AREA_PER_FRAME))
				.collect(Collectors.joining());
	}

	private static String buildStartFrame(String value) {
		String valueText = padToSameLength(value);
		return FRAME_EDGE + valueText + FRAME_EDGE;
	}

	private static String buildNoneStartFrame(String value) {
		String valueText = padToSameLength(value);
		return valueText + FRAME_EDGE;
	}

	private static String padToSameLength(String value) {
		StringBuilder stringBuilder = new StringBuilder(AREA_PER_FRAME);
		int paddingStartIndex = getPaddingStartIndex(value);
		stringBuilder.replace(paddingStartIndex, paddingStartIndex + value.length(), value);
		return stringBuilder.toString();
	}

	private static int getPaddingStartIndex(String value) {
		return Math.round((AREA_PER_FRAME.length() - value.length())/2F);
	}

	private static String getFormattedSequence(int sequence) {
		if(sequence < DOUBLE_DIGIT_START_VALUE) {
			return DOUBLE_DIGIT_FORMAT_PREFIX + sequence;
		}
		return String.valueOf(sequence);
	}
}
