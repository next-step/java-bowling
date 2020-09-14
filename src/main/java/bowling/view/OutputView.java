package bowling.view;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.util.Strings;

import bowling.frame.Frames;
import bowling.user.Player;
import bowling.user.Players;

public class OutputView {

	private static final String NO_SPACE_PARAM = "|%s  ";
	private static final String ONE_SPACE_PARAM = "| %s  ";
	private static final String TWO_SPACE_PARAM = "|  %s  ";
	private static final int START_FRAME_NO = 1;
	private static final int FINAL_FRAME_NO = 10;
	private static final int DISPLAY_COLUMN_COUNT = 11;

	public static void viewInit(Players players) {
		IntStream.range(0, players.size())
				 .forEach(i -> viewInit(players.indexOf(i)));
	}

	private static void viewInit(Player player) {

		String line2 = String.format("|%s   |     |     |     |     |     |     |     |     |     |     |", player.getName());
		String line3 = "|      |     |     |     |     |     |     |     |     |     |     |";

		System.out.println(getFrameLine());
		System.out.println(line2);
		System.out.println(line3);
	}

	private static String getFrameLine() {
		String name = "| NAME ";
		String frameNos = IntStream.range(START_FRAME_NO, DISPLAY_COLUMN_COUNT)
								   .mapToObj(i -> i < FINAL_FRAME_NO ? String.format("0%d", i) : String.format("%d", i))
								   .map(frameNo -> makeLine(frameNo))
								   .collect(joining());
		return name + frameNos + "|";
	}

	private static String makeLine(String param) {
		if (param.length() == 3) {
			return String.format(NO_SPACE_PARAM, param);
		}
		if (param.length() == 2) {
			return String.format(ONE_SPACE_PARAM, param);
		}
		return String.format(TWO_SPACE_PARAM, param);

	}

	public static void viewPitchingResults(Players players, List<Frames> framesList) {
		IntStream.range(0, players.size())
				 .forEach(i -> viewPitchingResult(players.indexOf(i), framesList.get(i)));
	}

	public static void viewPitchingResult(Player player, Frames frames) {
		String name = String.format("|%s   ", player.getName());
		String knockingDownPinsSigns = getKnockingDownPinsSignsString(frames);

		System.out.println(getFrameLine());
		System.out.println(name + knockingDownPinsSigns + "|");
		System.out.println("|      " + frames.getScoresToDate()
											 .stream()
											 .map(score -> makeLine(score.isDoneCalculates() ? String.valueOf(score.getScore()) : " "))
											 .collect(joining())
						   + IntStream.range(0, DISPLAY_COLUMN_COUNT - frames.getScoresToDate().size())
									  .mapToObj(i -> makeLine(" "))
									  .collect(joining())
						  );

	}

	private static String getKnockingDownPinsSignsString(Frames frames) {
		return IntStream.range(0, FINAL_FRAME_NO)
						.mapToObj(i -> frames.getKnockingDownPinsSignsOf(i)
											 .stream()
											 .collect(joining("|")))

						.map(sign -> makeLine(sign.equals(Strings.EMPTY) ? " " : sign))
						.collect(joining());
	}
}
