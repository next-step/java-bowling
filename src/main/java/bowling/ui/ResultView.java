package bowling.ui;

import java.util.stream.Stream;

import bowling.domain.BowlingTurn;
import bowling.domain.frame.Frame;

public class ResultView {
    private static final int LAST_FRAME = 10;
    private static final String PARTITION = "|";
    private static final String FRAME_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";



    public static void printInitBowlingBoard(BowlingTurn bowlingTurn) {
        printTitleFrames();
        System.out.print(PARTITION + formatting(bowlingTurn.player()));
        printEmptyFrames(0);
    }

    public static void printBowlingBoard(BowlingTurn bowlingTurn) {
        printTitleFrames();
        printFrameStates(bowlingTurn);
        printEmptyFrames(bowlingTurn.currentFrameSize());
    }

    private static void printTitleFrames() {
        System.out.println(FRAME_TITLE);
    }

    private static void printEmptyFrames(int size) {
        Stream.generate(() -> formatting(""))
            .limit(LAST_FRAME - size)
            .forEach(System.out::print);
        System.out.println("\n");
    }

    private static void printFrameStates(BowlingTurn bowlingTurn) {
        System.out.print(PARTITION + formatting(bowlingTurn.player()));

        bowlingTurn.frames().stream()
            .map(Frame::frameState)
            .map(ResultView::formatting)
            .forEach(System.out::print);
    }

    private static String formatting(String input) {
        return String.format("  %-3s " + PARTITION, input);
    }
}
