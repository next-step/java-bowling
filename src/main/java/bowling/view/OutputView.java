package bowling.view;

import static java.lang.System.*;

import java.util.List;
import java.util.stream.Collectors;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.player.Player;
import bowling.score.Score;

public class OutputView {

	private static final String NAME_HEADER = "|   %3s  |";
	private static final String SCORE_HEADER = "|  SCORE |";

	private static final String RESULT_FORMAT = "  %-5s |";
	private static final String SCORE_FORMAT = "    %-3d |";
	private static final String EMPTY_FORMAT = "        |";

	public void showResult(Player player, Frames frames) {
		showHeader();
		List<Frame> values = frames.values();
		showContent(player, values);
		showScore(values);
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
		return String.format(NAME_HEADER, player.name());
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

	private void showScore(List<Frame> frames) {
		String result = SCORE_HEADER
			+ createScore(frames)
			+ createEmptyFrame(frames.size());

		out.println(result);
	}

	private String createScore(List<Frame> frames) {
		return frames.stream()
			.map(frame -> formatScore(frames, frame))
			.collect(Collectors.joining());
	}

	private String formatScore(List<Frame> frames, Frame frame) {
		if (frame.score() == Score.UNAVAILABLE_NOW) {
			return EMPTY_FORMAT;
		}
		return sumTotalScore(frames, frame.number());
	}

	private String sumTotalScore(List<Frame> frames, int currentNumebr) {
		int sum = frames.stream()
			.limit(currentNumebr)
			.mapToInt(Frame::score)
			.sum();
		return String.format(SCORE_FORMAT, sum);
	}
}
