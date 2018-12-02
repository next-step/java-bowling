package view;

import domain.BowlingScoreBoard;
import domain.Player;

import java.util.StringJoiner;

import static org.apache.commons.lang3.StringUtils.center;

/**
 * Created by hspark on 22/11/2018.
 */
public class OutputView {
	private static final int PADDING_SIZE = 6;
	private static final String FRAME_NUMBER_PREFIX = "0";
	private static final String EMPTY_STR = "";

	public static void printBoard(Player player, BowlingScoreBoard scoreBoard) {
		StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
		stringJoiner.add(center("NAME", PADDING_SIZE));
		for (int i = 1; i <= 10; i++) {
			String str = center(i < 10 ? FRAME_NUMBER_PREFIX + i : EMPTY_STR + i, PADDING_SIZE);
			stringJoiner.add(str);
		}
		stringJoiner.add("\n");
		stringJoiner.add(center(player.toString(), PADDING_SIZE));

		for (String score : scoreBoard.gameResult()) {
			String str = center(score, PADDING_SIZE);
			stringJoiner.add(str);
		}

		stringJoiner.add("\n");
		stringJoiner.add(center(EMPTY_STR, PADDING_SIZE));

		for (Integer score : scoreBoard.scores()) {
			String str = center(score.toString(), PADDING_SIZE);
			stringJoiner.add(str);
		}
		System.out.println(stringJoiner.toString());
	}

}
