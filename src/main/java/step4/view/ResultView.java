package step4.view;

import step4.domain.Frame;

public class ResultView {
    private static String RESULT_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String PERSON_NNAME = "|  %3s |";
    private static String EMPTY_COLUMN = " %3s  |";

    public static void printColumn() {
        System.out.println(RESULT_COLUMN);
    }

    public static void printName(String personName) {
        System.out.printf(PERSON_NNAME, personName);
    }

    public static void printEmptyResult(int round) {
        for (int i = 0; i < round; i++) {
            System.out.printf(EMPTY_COLUMN, "");
        }
        System.out.println();
    }

    public static void printResult(Frame frame) {
        System.out.printf(EMPTY_COLUMN, frame.getScore());
    }
}
