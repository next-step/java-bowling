package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.common.Player;
import bowling.domain.frame.Frame;
import bowling.view.dto.PrintDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	private static void printFrames(final Player player, final List<Frame> frames) {
		final String prefix = framesPrefix(player);
		final String body = framesBody(frames);
		final int restFrameCount = 10 - frames.size();
		final String suffix = suffix(restFrameCount);

		System.out.println(prefix + body + suffix);
	}

	private static String framesPrefix(final Player player) {
		return String.format("%s%5s %s", DELIMITER, player.getName(), DELIMITER);
	}

	private static String framesBody(final List<Frame> frames) {
		return frames.stream()
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

	private static void printScores(final List<Frame> frames) {
		final String prefix = "|      |";
		final String body = scoresBody(frames);
		final int restFrameCount = (int) (10 - frames.stream()
				.filter(e -> e.caculateScore(frames).possiblecalculate())
				.count());
		final String suffix = suffix(restFrameCount);

		System.out.println(prefix + body + suffix);
	}

	private static String scoresBody(final List<Frame> frames) {
		if (frames.stream()
				.noneMatch(e -> e.caculateScore(frames).possiblecalculate())) {
			return "";
		}

		return frames.stream()
				.filter(e -> e.caculateScore(frames).possiblecalculate())
				.map(frame -> PrintDto.forScore(frame, frames))
				.map(PrintDto::toString)
				.collect(Collectors.joining());
	}
}
