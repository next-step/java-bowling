package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.utils.FrameViewUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class OutputView {

    private static final String DELIMITER = "|";
    private static final String BLANK = "";
    private static final String HEADER_FIRST_CELL_NAME = "NAME";

    private static final Map<Integer, Integer> prefixBlankMap;

    static {
        prefixBlankMap = new HashMap<>();
        prefixBlankMap.put(1, 3);
        prefixBlankMap.put(2, 4);
        prefixBlankMap.put(3, 5);
        prefixBlankMap.put(4, 5);
        prefixBlankMap.put(5, 6);
    }

    private OutputView() {
    }

    public static void scoreBoard(final BowlingGame bowlingGame) {
        headerOfScoreBoard();
        middleOfScoreBoard(bowlingGame.getPlayer(), bowlingGame.getFrames());
        footerOfScoreBoard(bowlingGame.getCanCalculateFrames());
    }

    public static void headerOfScoreBoard() {
        showFirstCell(HEADER_FIRST_CELL_NAME);
        IntStream.rangeClosed(1, Frame.MAX_ROUND_NUMBER)
                .boxed()
                .map((number) -> String.format("%02d", number))
                .map(OutputView::format)
                .forEach(System.out::print);
        System.out.println();
    }

    private static void middleOfScoreBoard(final Player player, final List<Frame> frames) {
        showFirstCell(player.getName());
        showScoreCells(frames);
        showFillEmptyCells(Frame.MAX_ROUND_NUMBER - frames.size());
        System.out.println();
    }

    private static void footerOfScoreBoard(final List<Frame> frames) {
        showFirstCell(BLANK);
        showTotalCells(frames);
        showFillEmptyCells(Frame.MAX_ROUND_NUMBER - frames.size());
        System.out.println();
    }

    private static void showFirstCell(final String str) {
        System.out.print(DELIMITER + format(str));
    }

    private static void showTotalCells(final List<Frame> frames) {
        int total = 0;
        for (Frame frame : frames) {
            total += frame.getScore();
            System.out.print(format(String.valueOf(total)));
        }
    }

    private static void showFillEmptyCells(final int length) {
        IntStream.rangeClosed(1, length)
                .forEach((ignore) -> System.out.print(format(BLANK)));
    }

    private static void showScoreCells(final List<Frame> frames) {
        frames.stream()
                .map((frame) -> String.join(DELIMITER, FrameViewUtil.show(frame)))
                .map(OutputView::format)
                .forEach(System.out::print);
    }

    private static String format(final String string) {
        return String.format("%-6s" + DELIMITER, addPrefixBlank(string));
    }

    private static String addPrefixBlank(final String string) {
        if (prefixBlankMap.containsKey(string.length())) {
            return String.format("%" + prefixBlankMap.get(string.length()) + "s", string);
        }
        return string;
    }
}