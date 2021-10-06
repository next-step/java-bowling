package step4.view;

public class ResultView {
    private static String RESULT_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String PERSON_NNAME = "|  %3s |";

    public static void printColumn() {
        System.out.println(RESULT_COLUMN);
    }

    public static void printName(String personName) {
        System.out.printf(PERSON_NNAME, personName);
    }
}