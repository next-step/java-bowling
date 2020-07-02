package bowling.view;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

public class OutputView {

	public static void printFrames(Player player, Frames frames) {
		printHeader(frames.getFrameList().size());
		printStatus(player, frames);
		printScores(frames);
	}

	private static void printHeader(int frameSize) {
		System.out.print("|  NAME  |");
		for (int index = 0; index < frameSize; index++) {
			int number = index + 1;
			System.out.printf("   %s   |", number < 10 ? "0" + number : number);
		}
		System.out.println();
	}

	private static void printStatus(Player player, Frames frames) {
		System.out.printf("|   %s   |", player.getName());
		frames.getFrameList().forEach(frame ->
			System.out.printf(" %s   |", ViewResult.printScores(frame.getScores())));
		System.out.println();
	}

	private static void printScores(Frames frames) {
		AtomicInteger sum = new AtomicInteger(0);
		System.out.print("|        |");
		frames.getFrameList().stream()
			.forEach(frame -> {
				sum.set(printScore(sum.get(), frame));
			});
		System.out.println();
	}

	private static int printScore(int sum, Frame frame) {
		Optional<Score> totalScore = frame.calculateFrameTotalScore();
		if (! totalScore.isPresent()) {
			System.out.print("        |");
			return sum;
		}
		sum += totalScore.map(Score::getScore).get();
		System.out.printf("%s| ", sum);
		return sum;
	}
}
