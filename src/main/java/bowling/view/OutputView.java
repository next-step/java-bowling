package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.Bowling;
import bowling.domain.common.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.view.dto.PrintDto;

public class OutputView {

	private static final String DELIMITER = "|";

	public static void printFrames(final Player player, final Bowling bowling) {
		printTitle();
		printFrames(player, bowling.getFrames());
		printScores(bowling.getFrames());
		System.out.println();
	}

	private static void printTitle() {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
	}

	private static void printFrames(final Player player, final Frames frames) {
		final String prefix = framesPrefix(player);
		final String body = framesBody(frames);
		final int restFrameCount = frames.restFrameCount();
		final String suffix = suffix(restFrameCount);

		System.out.println(prefix + body + suffix);
	}

	private static String framesPrefix(final Player player) {
		return String.format("%s%5s %s", DELIMITER, player.getName(), DELIMITER);
	}

	private static String framesBody(final Frames frames) {
		return frames.getFrames().stream()
			.map(PrintDto::forFrameResult)
			.map(PrintDto::toString)
			.collect(Collectors.joining());
	}

	private static String suffix(final int count) {
		return IntStream.range(0, count)
			.mapToObj(i -> PrintDto.of())
			.map(PrintDto::toString)
			.collect(Collectors.joining());
	}

	private static void printScores(final Frames frames) {
		final String prefix = "|      |";
		final String body = scoresBody(frames);
		final int restFrameCount = (int)(10 - frames.getFrames().stream()
			.filter(e -> e.caculateScore(frames).possiblecalculate())
			.count());
		final String suffix = suffix(restFrameCount);

		System.out.println(prefix + body + suffix);
	}

	private static String scoresBody(final Frames frames) {
		final List<Frame> calculableFrames = frames.getFrames().stream()
			.filter(e -> e.caculateScore(frames).possiblecalculate())
			.collect(Collectors.toList());

		int totalScore = 0;
		final List<PrintDto> printDtos = new ArrayList<>();

		for (final Frame frame : calculableFrames) {
			totalScore += frame.caculateScore(frames).getValue();
			printDtos.add(PrintDto.forScore(totalScore));
		}

		return printDtos.stream()
			.map(PrintDto::toString)
			.collect(Collectors.joining());
	}
}
