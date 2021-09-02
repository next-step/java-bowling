package bowling.view;

public class ResultView {
    private static final String RESULT_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRAME_INFO_MESSAGE = "프레임 투구 : ";
    private static final Integer RIGHT_SPACE_LENGTH = 4;

    public static void printResultColumn() {
        System.out.println(RESULT_COLUMN);
    }

    public static void printPlayerName(String name) {
        System.out.print("|  " + name + " |");
    }

    public static void printScore(String score) {
        System.out.print("  " + score);
        for (int i = 0; i < RIGHT_SPACE_LENGTH - score.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|");
    }

    public static void printSpace() {
        System.out.print("      |");
    }

    public static void printCurrentRound(Integer round, Integer score) {
        System.out.println(round + FRAME_INFO_MESSAGE + score);
    }
}
