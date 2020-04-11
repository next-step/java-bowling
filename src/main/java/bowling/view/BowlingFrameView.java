package bowling.view;

class BowlingFrameView {

    private static final String ROUND_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME = "|  %s |";
    private static final String READY_FRAME = "  %s   |";
    private static final String FINISH_FRAME = " %s  |";
    private static final String EMPTY_FRAME = "      |";
    private static final String SCORE_SINGLE_DIGIT = "  %s   |";
    private static final String SCORE_DOUBLE_DIGIT = "  %s  |";
    private static final String SCORE_THREE_DIGIT = "  %s |";

    static void getReadyFrame(String display) {
        System.out.print(String.format(READY_FRAME, display));
    }

    static void getName(String name) {
        System.out.print(String.format(NAME, name));
    }

    static void getFinishFrame(String display) {
        System.out.print(String.format(FINISH_FRAME, display));
    }

    static void getRoundBoard() {
        System.out.println(ROUND_BOARD);
    }

    static void getCollectFrame(int frameSize) {
        int size = 10 - frameSize;
        for (int i = 0; i < size; i++) {
            System.out.print(EMPTY_FRAME);
        }
        System.out.println();
    }

    static void getScoreFrame(int score) {
        if (score == -1) {
            System.out.print(String.format(SCORE_SINGLE_DIGIT, " "));
            return;
        }
        if (score < 10) {
            System.out.print(String.format(SCORE_SINGLE_DIGIT, score));
        } else if (score < 100) {
            System.out.print(String.format(SCORE_DOUBLE_DIGIT, score));
        } else {
            System.out.print(String.format(SCORE_THREE_DIGIT, score));
        }
    }
}
