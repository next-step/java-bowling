package bowling.view;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.view.dto.PrintBowlerDto;
import bowling.view.dto.PrintPitchStatesDto;
import bowling.view.dto.PrintScoreDto;

public class OutputView {

	public static final int END_COUNT = 10;

	public static final String DELIMITER = "|";
	public static final String BLANK = "";

	private OutputView() {
	}

	public static void printResultBoard(final List<PrintBowlerDto> dots) {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
		dots.forEach(OutputView::printGameResult);
		System.out.println();
	}

	private static String name(final String name) {
		return String.format("|%5s |", name);
	}

	private static void printGameResult(final PrintBowlerDto dto) {
		printFrame(dto);
		printScore(dto);
	}

	private static void printFrame(final PrintBowlerDto dto) {
		final String prefix = name(dto.getBowlerName());
		final String body = dto.getStates().stream()
			.map(PrintPitchStatesDto::printString)
			.map(state -> String.format("%5s ", state))
			.collect(Collectors.joining(DELIMITER, BLANK, DELIMITER));
		final String suffix = IntStream.range(dto.getCurrentFrameCount(), END_COUNT)
			.mapToObj(i -> String.format("%5s ", BLANK))
			.collect(Collectors.joining(DELIMITER));

		System.out.println(prefix
			+ body
			+ ((suffix.length() == 0) ? suffix : suffix + DELIMITER));
	}

	private static void printScore(final PrintBowlerDto dto) {
		final String prefix = name(BLANK);
		final String body = scoreBody(dto);
		final String suffix = IntStream.range(dto.getScores().size(), END_COUNT)
			.mapToObj(value -> String.format("%5s ", BLANK))
			.collect(Collectors.joining(DELIMITER));

		System.out.println(prefix
			+ ((body.length() == 0) ? body : body + DELIMITER)
			+ ((suffix.length() == 0) ? suffix : suffix + DELIMITER));
	}

	private static String scoreBody(final PrintBowlerDto dto) {
		final AtomicInteger score = new AtomicInteger(0);
		return dto.getScores().stream()
			.map(PrintScoreDto::getValue)
			.map(score::addAndGet)
			.map(value -> String.format("%5s ", value))
			.collect(Collectors.joining(DELIMITER));
	}
}
