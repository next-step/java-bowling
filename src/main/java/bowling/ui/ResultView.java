package bowling.ui;

import bowling.domain.Frame;

public class ResultView {
    private static final String BOARD_HEADER_FIRST_COLUMN = "| %4s |";
    private static final String NAME = "NAME";
    private static final String ROUND_FORMAT = "  %02d    |";
    private static final String RESULT_FORMAT = "  %-5s |";

    public static void printBoard(ResultFrameDto resultFrameDto) {
        System.out.println(getHeaderRow());
        System.out.println(getFramesRow(resultFrameDto));
    }

    private static String getHeaderRow() {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, NAME));
        for (int round = Frame.FIRST_FRAME; round <= Frame.FINAL_FRAME; round++) {
            sb.append(String.format(ROUND_FORMAT, round));
        }
        return sb.toString();
    }

    private static String getFramesRow(ResultFrameDto resultFrameDto) {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, resultFrameDto.getPlayerName()));
        fillShotHistory(resultFrameDto, sb);
        fillEmpty(resultFrameDto, sb);
        return sb.toString();
    }

    private static void fillShotHistory(ResultFrameDto resultFrameDto, StringBuilder sb) {
        resultFrameDto
                .generateSymbolStrings("|")
                .forEach(s -> sb.append(String.format(RESULT_FORMAT, s)));
    }

    private static void fillEmpty(ResultFrameDto resultFrameDto, StringBuilder sb) {
        for (int round = resultFrameDto.getLastFrame(); round < Frame.FINAL_FRAME; round++) {
            sb.append(String.format(RESULT_FORMAT, ""));
        }
    }
}
