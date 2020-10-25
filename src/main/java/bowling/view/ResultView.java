package bowling.view;

import bowling.frame.BowlingBoard;
import bowling.frame.FinalFrame;
import bowling.frame.Frame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String PRINT_HEADER_NAME_COLUMN = "| NAME |";
    private static final String PRINT_BODY_PLAYER_NAME_COLUMN = "| %4s |";
    private static final String PRINT_HEADER_FRAME_NUMBER_COLUMN = "  %02d  |";
    private static final String PRINT_RESULT_COLUMN = "  %-3s |";

    private ResultView() {
    }

    public static void printGameBoard(BowlingBoard bowling) {
        printHeader();
        printBody(bowling);
    }

    private static void printHeader() {
        System.out.print(PRINT_HEADER_NAME_COLUMN);

        IntStream.range(Frame.FIRST_FRAME_NUMBER, FinalFrame.FINISHED_FRAME_NUMBER)
                .forEach(frameNumber -> System.out.printf(PRINT_HEADER_FRAME_NUMBER_COLUMN, frameNumber));

        System.out.println();
    }

    private static void printBody(BowlingBoard bowling) {
        System.out.printf(PRINT_BODY_PLAYER_NAME_COLUMN, bowling.getBowlerName());

        List<String> results = printStateScore(bowling.getFrames());

        printProgressed(results);
        printBlankFrame(bowling);

        System.out.println();
    }

    private static void printProgressed(List<String> results) {
        results.stream()
                .map(ResultView::printScoreBlankRatio)
                .forEach(System.out::print);
    }

    private static void printBlankFrame(BowlingBoard bowling) {
        IntStream.range(bowling.getFrames().size(), Frame.FINAL_FRAME_NUMBER)
                .mapToObj(index -> printScoreBlankRatio(null))
                .forEach(System.out::print);
    }

    private static String printScoreBlankRatio(String result) {
        if (result == null) {
            return String.format(PRINT_RESULT_COLUMN, " ");
        }
        return String.format(PRINT_RESULT_COLUMN, result);
    }

    private static List<String> printStateScore(List<Frame> frames) {
        return frames.stream()
                .map(ResultView::getScoreMark)
                .collect(Collectors.toList());
    }

    private static String getScoreMark(Frame frame) {
        return frame.getState()
                .toString();
    }
}
