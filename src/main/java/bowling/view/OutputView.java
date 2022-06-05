package bowling.view;

import static java.lang.System.*;

import java.util.stream.Collectors;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.game.BowlingGame;
import bowling.game.EachGame;

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
		showEachGameContent(eachGame);
		showEachGameScore(eachGame);
	}

	private void showEachGameContent(EachGame eachGame) {
		String result = createNameFormat(eachGame.playerName())
			+ createResultFrames(eachGame.frames())
			+ createEmptyFrame(eachGame.frameNumber());
		out.println(result);
	}

	private String createNameFormat(String playerName) {
		return String.format(NAME_HEADER, playerName);
	}

	private String createResultFrames(Frames frames) {
		return frames.symbols()
			.stream()
			.map(symbol -> String.format(RESULT_FORMAT, symbol))
			.collect(Collectors.joining());
	}

	private String createEmptyFrame(int frameNumber) {
		int emptyResultCount = Frame.MAX_FRAME_NUMBER - frameNumber;
		return EMPTY_FORMAT.repeat(emptyResultCount);
	}

	private void showEachGameScore(EachGame eachGame) {
		String result = SCORE_HEADER
			+ createScore(eachGame.frames())
			+ createEmptyFrame(eachGame.frameNumber());

		out.println(result);
	}

	private String createScore(Frames frames) {
		return frames.values()
			.stream()
			.map(frame -> formatScore(frames, frame))
			.collect(Collectors.joining());
	}

	private String formatScore(Frames frames, Frame frame) {
		if (!frame.canScore()) {
			return EMPTY_FORMAT;
		}
		return String.format(SCORE_FORMAT, frames.sumUntil(frame.number()));
	}
}
