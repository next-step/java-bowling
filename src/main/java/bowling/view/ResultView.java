package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.Bowling;
import bowling.domain.Index;
import bowling.domain.frame.Frame;
import bowling.exception.UtilCreationException;

public final class ResultView {
	private static final StringBuilder BUILDER = new StringBuilder();

	private static final String HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
	private static final String NAME = "|  %s |";
	private static final String FRAME_RESULT = "  %-3s |";
	private static final String FRAME_EMPTY = "      |";

	private ResultView() {
		throw new UtilCreationException();
	}

	public static void print(Bowling bowling) {
		initializeBuilder();

		appendToBuilder(HEAD);
		appendNewlineToBuilder();

		appendToBuilder(String.format(NAME, bowling.getPlayerName()));
		appendToBuilder(createBody(bowling.getFrames()));
		appendNewlineToBuilder();

		printBuilder();
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
