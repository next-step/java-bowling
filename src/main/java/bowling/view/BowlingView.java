package bowling.view;

class BowlingView {

    private static final String ROUND_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String READY_FRAME = "  %s   |";
    private static final String FINISH_FRAME = " %s  |";
    private static final String EMPTY_FRAME = "      |";
    private static final String FINAL_FINISH_FRAME = " %s|";

    static void getReadyFrame(String display) {
        System.out.print(String.format(READY_FRAME, display));
    }

    static void getFinishFrame(String display) {
        System.out.print(String.format(FINISH_FRAME, display));
    }

    static void getFinalFinishFrame(String display) {
        System.out.print(String.format(FINAL_FINISH_FRAME, display));
    }

    static void getRoundBoard() {
        System.out.println(ROUND_BOARD);
    }

    static void getCollectFrame(int frameSize) {
        int size = 10 - frameSize;
        for (int i = 0; i < size ; i++) {
            System.out.print(EMPTY_FRAME);
        }
        System.out.println();
    }
}
