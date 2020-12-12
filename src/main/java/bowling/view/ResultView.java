package bowling.view;

import bowling.domain.Frames;

public class ResultView {
    private static final String FRAME = "|";
    private static final String NAME = "NAME";
    public static final String title = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";


    public static void printTitle() {
        System.out.println(title);
    }

    public static void printBowlResult(Frames frames) {
        frames.getFrames();
    }
}
