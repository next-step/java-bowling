package bowling.view;

import java.util.Objects;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class OutputView {

	public static void printFrames(Player player, Frames frames) {
		printHeader(frames.getFrameList().size());
		printFooter(player, frames);
	}

	private static void printHeader(int frameSize) {
		System.out.print("|  NAME  |");
		for (int index = 0; index < frameSize; index++) {
			int number = index + 1;
			System.out.printf("   %s   |", number < 10 ? "0" + number : number);
		}
		System.out.println();
	}

	private static void printFooter(Player player, Frames frames) {
		System.out.printf("|   %s   |", player.getName());
		frames.getFrameList().forEach(frame ->
			System.out.printf(" %s   |", printResult(frame)));
		System.out.println();
	}

	private static String printResult(Frame frame) {
		Scores scores = frame.getScores();
		StringBuilder builder = new StringBuilder();
		Score first = scores.getFirst();
		Score second = scores.getSecond();

		if (Objects.nonNull(first)) {
			builder.append(first.getScore());
		}
		builder.append("/");
		if (Objects.nonNull(second)) {
			builder.append(second.getScore());
		}
		return builder.toString();
	}
}
