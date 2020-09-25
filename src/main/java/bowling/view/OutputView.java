package bowling.view;

import bowling.domain.DownedPinCount;
import bowling.domain.game.Game;
import bowling.domain.game.Games;
import bowling.domain.score.Score;
import bowling.domain.state.State;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.state.InitState.EMPTY;

public class OutputView {
	private static final String AREA_PER_FRAME = "      ";
	private static final String FRAME_EDGE = "|";
	private static final String NAME_HEADER = "NAME";
	private static final int DOUBLE_DIGIT_START_VALUE = 10;
	private static final String DOUBLE_DIGIT_FORMAT_PREFIX = "0";

	public static void showInitializedGame(Games games) {
		System.out.println(getHeaderAreaText());
		games.getGames()
				.forEach(game -> {
					System.out.println(getInitValueAreaText(game));
					System.out.println(getInitScoreAreaText());
				});
	}

	public static void showDashBoard(Games games) {
		System.out.println(getHeaderAreaText());
		games.getGames()
				.forEach(game -> {
					System.out.println(getCurrentValueAreaText(game));
					System.out.println(getCurrentScoreAreaText(game));
				});
	}

	private static String getHeaderAreaText() {
		return buildStartFrame(NAME_HEADER) + getHeaderTitlePerFrame();
	}

	private static String getInitValueAreaText(Game game) {
		return buildStartFrame(game.getPlayersName()) + getEmptyTenFrames();
	}

	private static String getInitScoreAreaText() {
		return buildStartFrame(AREA_PER_FRAME) + getEmptyTenFrames();
	}

	private static String getCurrentValueAreaText(Game game) {
		return buildStartFrame(game.getPlayersName()) + getCurrentValueTextPerFrame(game.getStateGroupedBy());
	}

	private static String getCurrentScoreAreaText(Game game) {
		return buildStartFrame(AREA_PER_FRAME) + getCurrentScorePerFrame(game.getScoreGroupedBy());
	}

	private static String getHeaderTitlePerFrame() {
		return IntStream.rangeClosed(1,10)
				.mapToObj(index -> buildNoneStartFrame(getFormattedSequence(index)))
				.collect(Collectors.joining());
	}

	private static String getEmptyTenFrames() {
		return IntStream.rangeClosed(1,10)
				.mapToObj(index -> buildNoneStartFrame(AREA_PER_FRAME))
				.collect(Collectors.joining());
	}

	private static String getCurrentValueTextPerFrame(Map<Integer, State> grouped) {
		return IntStream.rangeClosed(1,10)
				.mapToObj(index -> buildNoneStartFrame(getValueText(grouped.get(index))))
				.collect(Collectors.joining());
	}

	private static String getCurrentScorePerFrame(Map<Integer, Score> grouped) {
		return IntStream.rangeClosed(1,10)
				.mapToObj(index -> buildNoneStartFrame(getScoreText(grouped.get(index))))
				.collect(Collectors.joining());
	}

	private static String getValueText(State state) {
		if(state == null) {
			return AREA_PER_FRAME;
		}
		return state.reportState();
	}

	private static String getScoreText(Score score) {
		if(score == null || score.getValue() == DownedPinCount.NO_PIN_DOWN) {
			return AREA_PER_FRAME;
		}
		return String.valueOf(score.getValue());
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
		if(value == EMPTY) {
			return AREA_PER_FRAME;
		}
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
