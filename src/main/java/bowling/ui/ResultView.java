package bowling.ui;

import bowling.domain.Shot;
import bowling.domain.Frames;

import java.util.stream.Collectors;

public class ResultView {
    private static final String BOARD_HEADER_FIRST_COLUMN = "| %4s |";
    private static final String NAME = "NAME";
    private static final String ROUND_FORMAT = "  %02d    |";
    private static final String RESULT_FORMAT = "  %-5s |";

    public static void printBoard(Frames frames) {
        System.out.println(getHeaderRow());
        System.out.println(getFramesRow(frames));
    }

    private static String getHeaderRow() {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, NAME));
        for (int round = Frames.FIRST_FRAME; round <= Frames.FINAL_FRAME; round++) {
            sb.append(String.format(ROUND_FORMAT, round));
        }
        return sb.toString();
    }

    private static String getFramesRow(Frames frames) {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, frames.getPlayerName()));
        fillShotHistory(frames, sb);
        fillEmpty(frames, sb);
        return sb.toString();
    }

    private static void fillShotHistory(Frames frames, StringBuilder sb) {
        frames.stream()
                .forEach(f ->
                        sb.append(String.format(RESULT_FORMAT,
                                f.getShotHistory().stream().map(Shot::getSymbol).collect(Collectors.joining("|"))
                        )));
    }

    private static void fillEmpty(Frames frames, StringBuilder sb) {
        for (int round = frames.getCurrentFrameNo(); round < Frames.FINAL_FRAME; round++) {
            sb.append(String.format(RESULT_FORMAT, ""));
        }
    }
}
