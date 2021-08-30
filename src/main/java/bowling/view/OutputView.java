package bowling.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

public class OutputView {

	private static final String DELIMITER = "|";

	public static void printFrames(final Player player, final Bowling bowling) {
		printTitle();
		printFrames(player, bowling.getFrames());
		System.out.println();
	}

	private static void printTitle() {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
	}

	private static void printFrames(final Player player, final List<Frame> frames) {
		final String prefix = framesPrefix(player);
		final String body = framesBody(frames);
		final String suffix = framesSuffix(frames);

		System.out.println(prefix + body + suffix);
	}

	private static String framesPrefix(final Player player) {
		return String.format("%s%5s %s", DELIMITER, player.getName(), DELIMITER);
	}

	private static String framesBody(final List<Frame> frames) {
		return frames.stream()
			.map(frame -> ResultFlag.generateResultByFrame(frame, DELIMITER))
			.map(r -> String.format("%5s ", r))
			.collect(Collectors.joining(DELIMITER, "", DELIMITER));
	}

	private static String framesSuffix(final List<Frame> frames) {
		return IntStream.rangeClosed(1, 10 - frames.size())
			.mapToObj(r -> "      ")
			.collect(Collectors.joining(DELIMITER, "", DELIMITER));
	}
}
