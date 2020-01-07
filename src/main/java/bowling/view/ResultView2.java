package bowling.view;

import bowling.retry.Frame;

import java.util.List;

public class ResultView2 {

    private static final String ROUND_FRAMES = "  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_FRAME = "      |";
    private static final int FRAME_SIZE = 10;

    public static void printFrames(List<Frame> frames) {
        System.out.println(ROUND_FRAMES);
        int size = frames.size();
        for (Frame frame : frames) {
            System.out.print(frame.getStatus());
        }
        printEmptyFrames(size);
        System.out.println();
    }

    private static void printEmptyFrames(int size) {
        for (int i = 0; i < FRAME_SIZE - size; i++) {
            System.out.print(EMPTY_FRAME);
        }
    }
}
