package bowling.view;

import static java.lang.System.*;

import java.util.List;
import java.util.stream.Collectors;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.player.Player;

public class OutputView {

	private static final String NAME_FORMAT = "|   %s  |";
	private static final String RESULT_FORMAT = "  %-5s |";
	private static final String EMPTY_FORMAT = "        |";

	public void showResult(Player player, Frames frames) {
		showHeader();
		showContent(player, frames.values());
	}

	private void showHeader() {
		out.println(
			"|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |");
	}

	private void showContent(Player player, List<Frame> frames) {
		String result = createNameFormat(player)
			+ createResultFrames(frames)
			+ createEmptyFrame(frames.size());
		out.println(result);
	}

	private String createNameFormat(Player player) {
		return String.format(NAME_FORMAT, player.name());
	}

	private String createResultFrames(List<Frame> frames) {
		return frames.stream()
			.map(frame -> String.format(RESULT_FORMAT, frame.symbol()))
			.collect(Collectors.joining());
	}

	private String createEmptyFrame(int frameNumber) {
		int emptyResultCount = Frame.MAX_FRAME_NUMBER - frameNumber;
		return EMPTY_FORMAT.repeat(emptyResultCount);
	}
}
