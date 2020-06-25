package bowling.ui;

import bowling.domain.BowlingGameResult;
import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printEmptyResult(String userName) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                userName +
                " |      |      |      |      |      |      |      |      |      |      |");
    }

    public static void printPlayerResult(Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                player.getName() + " |" +
                parsePlayerResult(player.calculateResult()) +
                "|"
        );
    }

    public static void printBowlingGameResult(String playerName, List<BowlingGameResult> bowlingGameResults) {
        List<FrameResults> frameResults = bowlingGameResults.stream()
                .map(BowlingGameResult::getFrameResults)
                .collect(Collectors.toList());

        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println("|  " +
                playerName + " |" +
                parsePlayerResult(frameResults) +
                "|"
        );
    }

    private static String parsePlayerResult(List<FrameResults> frameResultsList) {
        return frameResultsList.stream()
                .map(OutputView::parseFrameResults)
                .map(OutputView::formatResult)
                .collect(Collectors.joining("|"));
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
        if (frameResult.length() == 3) {
            return "  " + frameResult + " ";
        }
        return " " + frameResult;
    }
}
