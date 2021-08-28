package bowling.view;

import java.util.stream.Collectors;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

public class ResultView {

	private static final String VERTICAL_LINE = "|";

	public static void printResultBoard(final Player player, final Frames frames) {
		printFrameTitleBoard();
		printFrameResultBoard(player, frames);
	}

	private static void printFrameTitleBoard() {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
	}

	private static void printFrameResultBoard(final Player player, final Frames frames) {
		final String prefix = String.format("%s%6s%s", VERTICAL_LINE, player.getName(), VERTICAL_LINE);
		final String result = frames.getValues().stream()
			.map(Frame::getResult)
			.map(r -> String.format("%6s", r))
			.collect(Collectors.joining(VERTICAL_LINE, prefix, VERTICAL_LINE));

		System.out.println(result);
	}
}
