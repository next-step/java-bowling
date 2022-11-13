package bowling.ui;

import bowling.domain.UserName;

import java.util.List;

public class ResultView {
    private static final String ROUND_RESULT_FORMAT = "  %-3s |";

    private static final String ROUND_GUIDE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";


    public static void printRoundResult(UserName userName, List<String> roundResult) {
        printScoreBoard(userName, roundResult);
    }

    public static void printScoreBoard(UserName userName, List<String> roundResult) {
        System.out.println(ROUND_GUIDE);
        StringBuilder printText = new StringBuilder(String.format(ROUND_RESULT_FORMAT, userName.getUserName()));
        for (int i = 0; i < 10; i++) {
            printText.append(String.format(ROUND_RESULT_FORMAT, getRoundResult(i, roundResult)));
        }
        printText.insert(0, "|");
        System.out.println(printText);
        System.out.println();
    }

    private static String getRoundResult(int index, List<String> roundResult) {
        if (roundResult == null || roundResult.size() <= index) {
            return "";
        }
        return roundResult.get(index);
    }
}
