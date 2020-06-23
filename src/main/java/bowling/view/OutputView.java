package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final int ZERO = 0;
    private static final int FRAME_BLANK_SUFFIX_COUNTS = 5;
    private static final int TOTAL_FRAME_COUNTS = 9;

    private OutputView() {
    }

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessage.DEFAULT_SCORE_BOARD, player.getName());
    }

    public static void printScoreBoard(Player player, Frames frames) {
        List<Frame> frameList = frames.getFrames();
        printDefaultLayout(player);
        frameList.forEach(OutputView::printVisibleFrames);
        printBlankFrames(frameList);
    }

    private static void printDefaultLayout(Player player) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessage.SCORE_BOARD_NAME, player.getName());
    }

    private static void printVisibleFrames(Frame frame) {
        List<String> scores = frame.getScores();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ViewMessage.FRAME_BLANK_PREFIX);
        scores.forEach(score -> appendScore(score, stringBuilder));
        appendFrameBlankSuffix(stringBuilder);
        System.out.print(stringBuilder.toString());
    }

    private static void appendScore(String score, StringBuilder stringBuilder) {
        if (stringBuilder.length() > ViewMessage.FRAME_BLANK_PREFIX.length()) {
            stringBuilder.append(ViewMessage.VERTICAL_LINE);
        }
        stringBuilder.append(score);
    }

    private static void appendFrameBlankSuffix(StringBuilder stringBuilder) {
        int blankSuffixCounts = FRAME_BLANK_SUFFIX_COUNTS - stringBuilder.length();
        IntStream.rangeClosed(ZERO, blankSuffixCounts)
                .forEach(i -> stringBuilder.append(ViewMessage.BLANK));
        stringBuilder.append(ViewMessage.VERTICAL_LINE);
    }

    private static void printBlankFrames(List<Frame> frameList) {
        int blankFrameCounts = TOTAL_FRAME_COUNTS - frameList.size();
        IntStream.rangeClosed(ZERO, blankFrameCounts)
                .forEach(i -> System.out.print(ViewMessage.BLANK_FRAME));
        System.out.println();
    }
}
