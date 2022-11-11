package bowling.ui;

import java.util.List;

public class ResultView {
    private static final String ROUND_RESULT_FORMAT = "  %-3s |";

    private static final String ROUND_RESULT_TITLE_FORMAT = "%d프레임 투구 : %d";

    private static final String ROUND_GUIDE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";


    public static void printRoundResult(String userName, List<String> roundResult) {
        System.out.println(String.format(ROUND_RESULT_TITLE_FORMAT, roundResult.size(), 0));
        System.out.println(ROUND_GUIDE);
        String printText = String.format(ROUND_RESULT_FORMAT, userName);
        for (String result : roundResult) {
            printText = printText + String.format(ROUND_RESULT_FORMAT, result);
        }
        printText = "|" + printText;
        System.out.println(printText);
    }
}
