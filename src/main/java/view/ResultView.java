package view;

import domain.bowling.BowlingOnBoard;
import domain.states.States;

import java.util.List;
import java.util.Optional;

public class ResultView {

	private static final int LAST_FRAME_INDEX = 9;

	private ResultView() {

	}

	public static void printCurrentScore(List<BowlingOnBoard> bowlingOnBoards) {
		System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10    |");
		for (BowlingOnBoard bowlingOnBoard : bowlingOnBoards) {
			printFrameResults(bowlingOnBoard);
			printScores(bowlingOnBoard);
		}
		System.out.println();
	}

	private static void printFrameResults(BowlingOnBoard bowlingOnBoards) {
		StringBuilder stringBuilder = new StringBuilder(String.format("|  %s |", bowlingOnBoards.getUserName()));
		List<States> statesList = bowlingOnBoards.getStates();
		for (int i = 0, end = statesList.size(); i < end; i++) {
			appendResult(stringBuilder, i, getFrameResult(statesList.get(i)));
		}
		System.out.println(stringBuilder.toString());
	}

	private static void appendResult(StringBuilder stringBuilder, int index, String target) {
		if (index != LAST_FRAME_INDEX) {
			stringBuilder.append(String.format("  %-3s |", target));
			return;
		}
		stringBuilder.append(String.format("  %-5s  |", target));
	}

	private static String getFrameResult(States states) {
		return String.join("|", states.getPhaseResultSign());
	}

	private static void printScores(BowlingOnBoard bowlingOnBoard) {
		StringBuilder stringBuilder = new StringBuilder("|      |");
		List<Optional<Integer>> scores = bowlingOnBoard.getScores();
		for (int i = 0, end = scores.size(); i < end; i++) {
			appendScore(stringBuilder, i, scores.get(i));
		}
		System.out.println(stringBuilder.toString());
	}

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private static void appendScore(StringBuilder stringBuilder, int i, Optional<Integer> score) {
		if (score.isPresent()) {
			appendResult(stringBuilder, i, String.valueOf(score.get()));
		} else {
			appendResult(stringBuilder, i, " ");
		}
	}

}
