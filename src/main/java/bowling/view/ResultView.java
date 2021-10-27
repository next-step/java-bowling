package bowling.view;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;
import static bowling.domain.frame.NormalFrame.FIRST_ROUND;

import bowling.domain.frame.FrameResult;
import bowling.domain.frame.FrameResults;
import bowling.domain.user.User;
import bowling.domain.userframeresult.UserFrameResults;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String NAME_BOARD = "| NAME |";
    private static final String BOARD_FORMAT = "  %02d  |";

    private static final String BOARD_NAME_FORMAT = "|  %-3s |";
    private static final String BOARD_SCORE_FORMAT = " %-5s|";

    private static final String BOARD_TOTAL_SCORE_FORMAT = "  %-4s|";
    
    private ResultView() {
    }

    public static void printBoard(UserFrameResults userFrameResults) {
        printScoreBoard();
        userFrameResults.stream()
            .forEach(result -> printBoard(result.user(), result.frameResults()));
    }

    public static void printBoard(User user, FrameResults frameResults) {
        printScoreResultBoard(user, frameResults);
    }

    public static void printScoreBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(NAME_BOARD);
        sb.append(IntStream.rangeClosed(FIRST_ROUND, FINAL_ROUND)
            .mapToObj(ResultView::toBoardFormat)
            .collect(Collectors.joining()));
        System.out.println(sb.toString());
    }

    private static String toBoardFormat(int num) {
        return String.format(BOARD_FORMAT, num);
    }

    public static void printScoreResultBoard(User user, FrameResults frameResults) {
        StringBuilder sb = new StringBuilder();
        sb.append(toNameFormat(user));

        sb.append(printFrameResultDescBoard(frameResults));
        sb.append(printRemainBoard(frameResults));
        sb.append("\n");

        sb.append(toBlankFormat());
        sb.append(printFrameResultScoreBoard(frameResults));
        sb.append(printRemainBoard(frameResults));
        System.out.println(sb.toString());
    }

    private static String printFrameResultDescBoard(FrameResults frameResults) {
        return frameResults.values()
            .map(FrameResult::desc)
            .map(ResultView::toScoreFormat)
            .collect(Collectors.joining());
    }

    private static String printRemainBoard(FrameResults frameResults) {
        return IntStream.range(frameResults.size(), FINAL_ROUND)
            .mapToObj(index -> toScoreFormat(""))
            .collect(Collectors.joining());
    }

    private static String printFrameResultScoreBoard(FrameResults frameResults) {
        return frameResults.values()
            .map(FrameResult::totalScoreToString)
            .map(ResultView::toTotalScoreFormat)
            .collect(Collectors.joining());
    }

    private static String toScoreFormat(String s) {
        return String.format(BOARD_SCORE_FORMAT, s);
    }

    private static String toTotalScoreFormat(String s) {
        return String.format(BOARD_TOTAL_SCORE_FORMAT, s);
    }

    private static String toNameFormat(User user) {
        return String.format(BOARD_NAME_FORMAT, user.value());
    }

    private static String toBlankFormat() {
        return String.format(BOARD_NAME_FORMAT, "");
    }

}
