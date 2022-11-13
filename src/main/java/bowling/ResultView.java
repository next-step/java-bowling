package bowling;

import java.util.List;

public class ResultView {

    public static void printResult(UserName name, Frames frames) {
        printRoundTemplate();
        printUserName(name);
    }

    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(UserName name) {
        System.out.print("|");
        System.out.printf("%5s ",name.getName());
    }
}
