package view;

import domain.BowlingScoreBoard;
import domain.Player;
import domain.frame.FrameResults;

import java.util.StringJoiner;

import static org.apache.commons.lang3.StringUtils.center;

/**
 * Created by hspark on 22/11/2018.
 */
public class OutputView {
	private static final int PADDING_SIZE = 6;
	private static final String FRAME_NUMBER_PREFIX = "0";
	private static final String EMPTY_STR = "";

	public static void printBoard(Player player, FrameResults frameResults) {
		StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
		stringJoiner.add(center("NAME", PADDING_SIZE));
		for (int i = 1; i <= 10; i++) {
			String str = center(i < 10 ? FRAME_NUMBER_PREFIX + i : EMPTY_STR + i, PADDING_SIZE);
			stringJoiner.add(str);
		}
		stringJoiner.add("\n");
		stringJoiner.add(center(player.toString(), PADDING_SIZE));

		for (String frameResult : frameResults.toResultList()) {
			String str = center(frameResult, PADDING_SIZE);
			stringJoiner.add(str);
		}

		stringJoiner.add("\n");
		stringJoiner.add(center(EMPTY_STR, PADDING_SIZE));

		for (Integer score : frameResults.toScoreList()) {
			String str = center(score.toString(), PADDING_SIZE);
			stringJoiner.add(str);
		}

		System.out.println(stringJoiner.toString());
	}

	public static void printBoard(BowlingScoreBoard scoreBoard) {
		StringJoiner stringJoiner = new StringJoiner("|", "|", "|");
		stringJoiner.add(center("NAME", PADDING_SIZE));
		for (int i = 1; i <= 10; i++) {
			String str = center(i < 10 ? FRAME_NUMBER_PREFIX + i : EMPTY_STR + i, PADDING_SIZE);
			stringJoiner.add(str);
		}
		stringJoiner.add("\n");
		stringJoiner.add(center(scoreBoard.getPlayer().toString(), PADDING_SIZE));

		for (String frameResult : scoreBoard.getResultList()) {
			String str = center(frameResult, PADDING_SIZE);
			stringJoiner.add(str);
		}

		stringJoiner.add("\n");
		stringJoiner.add(center(EMPTY_STR, PADDING_SIZE));

		for (Integer score : scoreBoard.getScoreList()) {
			String str = center(score.toString(), PADDING_SIZE);
			stringJoiner.add(str);
		}

		System.out.println(stringJoiner.toString());
	}

}
