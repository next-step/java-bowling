package bowling.ui;

import bowling.domain.frame.Frames;
import bowling.domain.game.Player;

import static bowling.domain.frame.Frames.MAX_FRAMES_SIZE;

public class OutputView {

    private static final String NAME_FORMAT = "| %s  ";
    private static final String FRAME_FORMAT = "|  %-3s ";
    private static final String EMPTY_FRAME = "|      ";

    private OutputView() {
    }

    public static void printBowling(Player player, Frames frames) {
        printHeader();
        printName(player.getName());
        printFrames(frames);
    }

    private static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10");
    }

    private static void printName(String name) {
        System.out.printf(NAME_FORMAT, name);
    }

    private static void printFrames(Frames frames) {
        frames.getFrames()
                .forEach(frame -> System.out.printf(FRAME_FORMAT, frame.toExpression()));

        int emptyFrameCount = MAX_FRAMES_SIZE - frames.getFrames().size();
        for (int i = 0; i < emptyFrameCount; i++) {
            printEmptyFrame();
        }

        System.out.println();
        System.out.println();
    }

    private static void printEmptyFrame() {
        System.out.print(EMPTY_FRAME);
    }
}
