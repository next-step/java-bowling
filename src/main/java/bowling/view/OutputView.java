package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

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
			System.out.printf(" %s   |", ViewResult.printScores(frame.getScores())));
		System.out.println();
	}
}
