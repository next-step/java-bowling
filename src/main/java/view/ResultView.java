package view;

import domain.bowlling.BowlingOnBoard;
import domain.frame.result.FrameResult;

public class ResultView {

	private ResultView() {

	}

	public static void printCurrentScore(BowlingOnBoard bowlingOnBoards) {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
		printFrameResults(bowlingOnBoards);
		System.out.println();
	}

	// TODO: 2019-11-18 마지막은 너비를 넓게
	private static void printFrameResults(BowlingOnBoard bowlingOnBoards) {
		StringBuilder stringBuilder = new StringBuilder(String.format("|  %s ", bowlingOnBoards.getUserName()));
		for (FrameResult frameResult : bowlingOnBoards.getFrameResults()) {
			stringBuilder.append(String.format("|  %s ", frameResult.getPhaseResults()));
		}
		stringBuilder.append("|");
		System.out.println(stringBuilder.toString());
	}

}
