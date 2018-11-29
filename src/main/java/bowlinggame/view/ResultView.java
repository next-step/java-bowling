package bowlinggame.view;

import bowlinggame.domain.Player;
import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.dto.PlayerResultDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ResultView {

	private static final String SEPARATOR = "|";
	private static final int EMPTY_SPACE = 2;
	private static final int VALUE_MAX_LENGTH = Player.MAX_NAME_LENGTH + 1;
	private static final int COLUMN_MAX_LENGTH = VALUE_MAX_LENGTH + EMPTY_SPACE;

	public static void printResult(PlayerResultDto playerResultDto) {
		printHeader();
		printPlayerResult(playerResultDto);
	}

	private static void printHeader() {
		String name = "NAME";
		List<String> frameNumbers = IntStream.rangeClosed(FrameNumber.FIRST, FrameNumber.LAST)
				.boxed()
				.map(frameNumber -> String.format("%02d", frameNumber))
				.collect(Collectors.toList());
		print(name, frameNumbers);
	}

	private static void printPlayerResult(PlayerResultDto playerResultDto) {
		String playerName = StringUtils.rightAlign(playerResultDto.getName(), VALUE_MAX_LENGTH);
		List<String> frameResults = IntStream.rangeClosed(FrameNumber.FIRST, FrameNumber.LAST)
				.mapToObj(frameNumber -> {
					List<String> results = playerResultDto.getFrameResult(frameNumber - 1);
					String frameResult = StringUtils.join(results, SEPARATOR);
					return StringUtils.centerAlign(frameResult, COLUMN_MAX_LENGTH);
				})
				.collect(Collectors.toList());
		print(playerName, frameResults);
	}

	private static void print(String playerName, List<String> frames) {
		String print = Stream.concat(Stream.of(playerName), frames.stream())
				.map(str -> StringUtils.centerAlign(str.toString(), COLUMN_MAX_LENGTH))
				.collect(Collectors.joining(SEPARATOR, SEPARATOR, SEPARATOR));
		System.out.println(print);
	}
}
