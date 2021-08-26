package bowling.view;

import bowling.domain.Board;
import java.util.stream.IntStream;

public class ResultView {
    private static final String NAME = "| NAME |";
    private static final String FRAME = "  %02d  |";
    private static final int FIRST_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;

    // 프레임 헤드
    private static void printHeader() {
        print(NAME);
        IntStream.rangeClosed(FIRST_FRAME_NUMBER, LAST_FRAME_NUMBER)
            .forEach(frameNumber -> {
                print(String.format(FRAME, frameNumber));
            });
        System.out.println();
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void printBoards(Board board) {
        board.getBoards().forEach(System.out::print);
    }

    public static void currentResult(Board board) {
        printHeader();
        printBoards(board);
        System.out.println();
    }

    public static void pitchedBall(int frameNum) {
        System.out.println(frameNum + " 프레임 투구");
    }
}
