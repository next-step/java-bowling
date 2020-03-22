package bowling.view;

import bowling.application.Response;
import bowling.domain.framestatus.FrameStatus;

import java.util.List;

public class ResultView {

    private static final String FRAME_COLUMN = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void print(Response response) {
        System.out.println(FRAME_COLUMN);
        List<FrameStatus> frameStatuses = response.getFrameStatuses();
        int size = frameStatuses.size();
        System.out.print(response.displayPlayer());
        for (FrameStatus frameStatus : frameStatuses) {
            System.out.print(frameStatus.display());
        }
        printDefaultFrame(size);
    }

    private static void printDefaultFrame(int size) {
        int emptySize = 10 - size;
        for (int i = 0; i < emptySize; i++) {
            System.out.print("|      ");
        }
        System.out.println("|");
        System.out.println();
    }
}
