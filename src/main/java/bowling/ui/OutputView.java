package bowling.ui;

import bowling.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printBowlingGameHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printEmptyResults(List<Player> players) {
        for (Player player : players) {
            printEmptyResult(player.getName());
        }
    }

    private static void printEmptyResult(String userName) {
        System.out.println("|  " +
                userName +
                " |      |      |      |      |      |      |      |      |      |      |");
        System.out.println("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    public static void printBowlingGameResult(String playerName, List<BowlingGameResult> bowlingGameResults) {
        List<FrameResults> frameResults = bowlingGameResults.stream()
                .map(BowlingGameResult::getFrameResults)
                .collect(Collectors.toList());

        List<FrameScore> frameScores = bowlingGameResults.stream()
                .map(BowlingGameResult::getFrameScore)
                .collect(Collectors.toList());

        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                playerName + " |" +
                parsePlayerResult(frameResults) +
                "|"
        );

        System.out.println("|      |" +
                parsePlayerScore(frameScores)
        );
    }

    private static String parsePlayerResult(List<FrameResults> frameResultsList) {
        return frameResultsList.stream()
                .map(OutputView::parseFrameResults)
                .map(OutputView::formatResult)
                .collect(Collectors.joining("|"));
    }

    private static String parsePlayerScore(List<FrameScore> frameScores) {
        List<Integer> completedScore = frameScores.stream()
                .filter(FrameScore::isComplete)
                .map(FrameScore::getScore)
                .collect(Collectors.toList());

        StringBuilder result = new StringBuilder();
        Integer totalScore = 0;
        for (Integer score : completedScore) {
            totalScore += score;
            result.append(formatResult(totalScore.toString()));
            result.append("|");
        }

        return result.toString();
    }

    private static String parseFrameResults(FrameResults frameResults) {
        return frameResults.getList().stream()
                .map(FrameResult::getValue)
                .collect(Collectors.joining("|"));
    }

    private static String formatResult(String frameResult) {
        if (frameResult.length() == 1) {
            return "  " + frameResult + "   ";
        }
        if (frameResult.length() == 2) {
            return "  " + frameResult + "  ";
        }
        if (frameResult.length() == 3) {
            return "  " + frameResult + " ";
        }
        return " " + frameResult;
    }
}
