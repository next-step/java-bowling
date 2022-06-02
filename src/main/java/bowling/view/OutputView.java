package bowling.view;

import static java.lang.System.*;

import java.util.List;
import java.util.stream.Collectors;

import bowling.frame.Frame;
import bowling.game.BowlingGame;
import bowling.game.EachGame;
import bowling.score.Score;

public class OutputView {

	private static final String NAME_HEADER = "|   %3s  |";
	private static final String SCORE_HEADER = "|  SCORE |";

	private static final String RESULT_FORMAT = "  %-5s |";
	private static final String SCORE_FORMAT = "    %-3d |";
	private static final String EMPTY_FORMAT = "        |";

	public void showResult2(BowlingGame bowlingGame) {
		showHeader();
		bowlingGame.eachGames()
			.forEach(this::showEachGameResult);
	}

	private void showHeader() {
		out.println(
			"|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |");
	}

	private void showEachGameResult(EachGame eachGame) {
		List<Frame> frames = eachGame.frames();
		showEachGameContent(eachGame.playerName(), frames);
		showEachGameScore(frames);
	}

	private void showEachGameContent(String playerName, List<Frame> frames) {
		String result = createNameFormat(playerName)
			+ createResultFrames(frames)
			+ createEmptyFrame(frames.size());
		out.println(result);
	}

	private String createNameFormat(String playerName) {
		return String.format(NAME_HEADER, playerName);
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

	private void showEachGameScore(List<Frame> frames) {
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

	private String sumTotalScore(List<Frame> frames, int currentNumber) {
		int sum = frames.stream()
			.limit(currentNumber)
			.mapToInt(Frame::score)
			.sum();
		return String.format(SCORE_FORMAT, sum);
	}
}
