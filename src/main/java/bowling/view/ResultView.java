package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.BowlingGame;
import bowling.domain.Index;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.exception.UtilCreationException;

public final class ResultView {
	private static final StringBuilder BUILDER = new StringBuilder();

	private static final String HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
	private static final String NAME = "|  %s |";

	private static final String FRAME_RESULT = "  %-3s |";
	private static final String FRAME_EMPTY = "      |";

	private static final String SCORE_LEFT_PADDING = "|      |";
	private static final String SCORE_RESULT = "  %-3s |";
	private static final String SCORE_EMPTY = "      |";

	private ResultView() {
		throw new UtilCreationException();
	}

	public static void print(List<BowlingGame> bowlingGames) {
		initializeBuilder();

		appendHead();

		bowlingGames.forEach(bowlingGame -> {
			appendPlayerAndBody(bowlingGame);
			appendScore(bowlingGame);
		});

		printBuilder();
	}

	private static void appendHead() {
		appendToBuilder(HEAD);
		appendNewlineToBuilder();
	}

	private static void appendPlayerAndBody(BowlingGame bowlingGame) {
		appendToBuilder(String.format(NAME, bowlingGame.getPlayerName()));
		appendToBuilder(createBody(bowlingGame.getFrames()));
		appendNewlineToBuilder();
	}

	private static String createBody(List<Frame> frames) {
		String body = frames.stream()
			.map(frame -> String.format(FRAME_RESULT, frame.symbol()))
			.collect(joining());

		String emptyBody = IntStream.rangeClosed(1, Index.MAX_OF_INDEX - frames.size())
			.mapToObj(i -> FRAME_EMPTY)
			.collect(joining());

		return body + emptyBody;
	}

	private static void appendScore(BowlingGame bowlingGame) {
		appendToBuilder(createScore(bowlingGame.getFrames()));
		appendNewlineToBuilder();
	}

	private static String createScore(List<Frame> frames) {
		String body = frames.stream()
			.map(frame -> {
				if (frame.score() != Score.INCALCULABLE_SCORE) {
					return sumTotalScore(frames, frames.indexOf(frame) + 1);
				}
				return SCORE_EMPTY;
			})
			.collect(joining());

		String emptyBody = IntStream.rangeClosed(1, Index.MAX_OF_INDEX - frames.size())
			.mapToObj(i -> FRAME_EMPTY)
			.collect(joining());

		return SCORE_LEFT_PADDING + body + emptyBody;
	}

	private static String sumTotalScore(List<Frame> frames, int limit) {
		int sum = frames.stream()
			.limit(limit)
			.mapToInt(Frame::score)
			.sum();
		return String.format(SCORE_RESULT, sum);
	}

	private static void initializeBuilder() {
		BUILDER.setLength(0);
	}

	private static void appendToBuilder(Object input) {
		BUILDER.append(input);
	}

	private static void appendNewlineToBuilder() {
		appendToBuilder(System.lineSeparator());
	}

	private static void printBuilder() {
		System.out.println(BUILDER);
	}
}
