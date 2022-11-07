package bowling.view;

public class ResultView {

    /**
     * | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
     * |  PJS |  X   |  8   |      |      |      |      |      |      |      |      |
     */

    private ResultView() {}

    public static void resultTitlePrint(final FramesResult framesResult) {

        System.out.println(framesResult.frameNumberLine());
        System.out.println(framesResult.frameScores() + System.lineSeparator());
    }
}
