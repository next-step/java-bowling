package step4.view;

import step4.domain.Frame;

public class ResultView {
    private static String RESULT_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String FIRST_COLUMN = "|  %4s |";
    private static String MIDDLE_COLUMN = " %3s  |";

    public static void printMainColumn() {
        printFirstColumn("NAME");
        for (int i = 1; i <= 10; i++) {
            printMiddleColumn(Integer.toString(i));
        }
        System.out.println();
    }

    public static void printFirstColumn(String target) {
        System.out.printf(FIRST_COLUMN, target);
    }

    public static void printMiddleColumn(String target) {
        System.out.printf(MIDDLE_COLUMN, target);
    }

    public static void printEmptyColumn(int round) {
        for (int i = 0; i < round; i++) {
            System.out.printf(MIDDLE_COLUMN, "");
        }
        System.out.println();
    }



    public static void printResult(int score) {
        System.out.printf(MIDDLE_COLUMN, score);
    }

    public static void printSymbol(Frame frame) {
        System.out.printf(MIDDLE_COLUMN, frame.getSymbol());
    }
}
