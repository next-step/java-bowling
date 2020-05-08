package bowling.ui;

import java.util.List;

public class ResultView {
    private static final int MAX_RECORD_LENGTH = 5;

    public static void printBoard(String name, List<String> result) {
        printBoardHeader();
        printUserRecord(name, result);
    }

    private static void printUserRecord(String name, List<String> result) {
        System.out.print(String.format("|  %s |", name));
        for (String record : result) {
            printFrameRecord(record);
        }

        printBoardPadding(result);
        printNewLine();
    }

    private static void printFrameRecord(String record) {
        if (record.length() == MAX_RECORD_LENGTH) {
            System.out.print(String.format(" %s|", record));
        } else {
            System.out.print(String.format("  %-3s |", record));
        }
    }

    private static void printBoardPadding(List<String> result) {
        for (int i = 0; i < 10 - result.size(); i++) {
            System.out.print("      |");
        }
    }

    private static void printNewLine() {
        System.out.println("");
    }

    private static void printBoardHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }
}
