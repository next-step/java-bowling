package bowling.view;

public class ResultView {

    private static final int WIDTH = 6;

    String RESULT_DELIMITER = "|";

    private ResultView() {

    }
/*

    public static void printFrame(BowlingFrames bowlingFrames, Player player) {
        printHeader(bowlingFrames.getSize());
        printRow(bowlingFrames, player);
    }

    private static void printRow(BowlingFrames bowlingFrames, Player player) {
        System.out.printf("|%s|", getColumn(player.getName()));
        for (int i = 0; i < bowlingFrames.getSize(); i++) {
            System.out.printf("%s|", getColumn(bowlingFrames.getFrame(i).getResult()));
        }
        System.out.println();
    }

    private static void printHeader(int frameSize) {
        System.out.print("| NAME |");
        for (int i = 1; i <= frameSize; i++) {
            System.out.printf("%s|", getColumn(String.format("%02d", i)));
        }
        System.out.println();
    }

    private static String getColumn(String data) {
        int blankSize = WIDTH - data.length();
        String leftBlank = " ".repeat(blankSize/2 + (blankSize%2));
        String rightBlank = " ".repeat(blankSize/2);
        return leftBlank + data + rightBlank;
    }
*/

}
