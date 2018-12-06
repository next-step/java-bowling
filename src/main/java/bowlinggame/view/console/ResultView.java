package bowlinggame.view.console;

import bowlinggame.domain.Player;
import bowlinggame.domain.PlayerResult;
import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.view.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ResultView {

	private static final String SEPARATOR = "|";
	private static final int EMPTY_SPACE = 2;
	private static final int VALUE_MAX_LENGTH = Player.MAX_NAME_LENGTH + 1;
	private static final int COLUMN_MAX_LENGTH = VALUE_MAX_LENGTH + EMPTY_SPACE;
	private static final String EMPTY_COLUMN = StringUtils.rightAlign("", VALUE_MAX_LENGTH);

	public static void printResult(PlayerResult playerResult) {
		printHeader();
		printPlayerResult(playerResult);
	}

	private static void printHeader() {
		String name = "NAME";
		List<String> frameNumbers = IntStream.rangeClosed(FrameNumber.FIRST, FrameNumber.LAST)
				.boxed()
				.map(frameNumber -> String.format("%02d", frameNumber))
				.collect(Collectors.toList());
		print(name, frameNumbers);
	}

	private static void printPlayerResult(PlayerResult playerResult) {
		printRollResult(playerResult);
		printScore(playerResult);
	}

	private static void printRollResult(PlayerResult playerResult) {
		String playerName = StringUtils.rightAlign(playerResult.getName(), VALUE_MAX_LENGTH);
		List<String> frameResults = IntStream.range(FrameNumber.FIRST - 1, FrameNumber.LAST)
				.mapToObj(frameNumber -> StringUtils.centerAlign(playerResult.getRollResult(frameNumber), COLUMN_MAX_LENGTH))
				.collect(Collectors.toList());
		print(playerName, frameResults);
	}


	private static void printScore(PlayerResult playerResult) {
		List<String> frameResults = IntStream.range(FrameNumber.FIRST - 1, FrameNumber.LAST)
				.mapToObj(frameNumber -> {
					String score = playerResult.getScore(frameNumber);
					return StringUtils.centerAlign(score, COLUMN_MAX_LENGTH);
				})
				.collect(Collectors.toList());
		print(EMPTY_COLUMN, frameResults);
	}

	private static void print(String playerName, List<String> frames) {
		String print = Stream.concat(Stream.of(playerName), frames.stream())
				.map(str -> StringUtils.centerAlign(str.toString(), COLUMN_MAX_LENGTH))
				.collect(Collectors.joining(SEPARATOR, SEPARATOR, SEPARATOR));
		System.out.println(print);
	}
}
