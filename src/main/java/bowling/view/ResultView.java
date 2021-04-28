package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.Score;

public class ResultView {
	private static final String SEPARATOR = "|";
	private static final String BLANK = "   ";
	private static final int MAX_BLANK_SIZE = 6;

	public static void printBoard(Frames frames, Player player) {
		printFrame(frames);

		System.out.println();
		printScore(frames, player);

		System.out.println();
	}

	private static void printFrame(Frames frames) {
		System.out.print(SEPARATOR + "  NAME  " + SEPARATOR);
		frames.frames().forEach(frame -> {
			System.out.print(BLANK + String.format("%02d", frame.frameNumber()) + BLANK + SEPARATOR);

		});
	}

	private static void printScore(Frames frames, Player player) {
		System.out.print(SEPARATOR + String.format("%7s ", player.toString()));
		frames.frames().forEach(frame -> {
			printScorePerFrame(frame);
		});
		System.out.println(SEPARATOR);
	}

	private static void printScorePerFrame(Frame frame) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEPARATOR);
		sb.append(BLANK);
		for (int i = 0; i < frame.playCount(); i++) {
			int hitSize = frame.pinStatus().pinSize(i);
			int previousHitSize = i == 0 ? 0 : frame.pinStatus().pinSize(i - 1);
			int firstHitSize = i == 2 ? frame.pinStatus().pinSize(i - 2) : 0;
			sb.append(makeMark(hitSize, previousHitSize, firstHitSize, i));
		}
		System.out.print(String.format("%-9s", sb.toString()));
	}

	private static StringBuilder makeMark(int hitSize, int previousHitSize, int firstHitSize, int i) {
		StringBuilder sb = new StringBuilder();

		String mark = Score.score(hitSize, previousHitSize, firstHitSize, i + 1).mark();
		String sbMark = mark.equals("-1") ? String.valueOf(hitSize) : mark;
		if (i > 0) {
			sb.append(SEPARATOR);
		}
		sb.append(sbMark);

		return sb;
	}

}
