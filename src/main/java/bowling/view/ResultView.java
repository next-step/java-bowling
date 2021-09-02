package bowling.view;

public class ResultView {
    private static final String RESULT_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printResultColumn() {
        System.out.println(RESULT_COLUMN);
    }

    public static void printPlayerName(String name) {
        System.out.print("|  " + name + " |");
    }

    public static void printScore(String score) {
        System.out.print("  " + score + "   |");
    }

    public static void printSpace() {
        System.out.print("      |");
    }

    public static void printCurrentRound(Integer round, Integer score) {
        System.out.println(round + "프레임 투구 : " + score);
    }
}
