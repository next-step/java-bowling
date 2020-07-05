package bowling.ui;

import bowling.domain.Frame;
import bowling.domain.Pin;

public class ResultView {
    private static final String SHOT_FORMAT = "%s's turn : %s";
    private static final String BOARD_HEADER_FIRST_COLUMN = "| %4s |";
    private static final String NAME = "NAME";
    private static final String ROUND_FORMAT = "  %02d    |";
    private static final String RESULT_FORMAT = "  %-5s |";

    public static void printNextPin(String playerName, Pin nextPin) {
        System.out.println(String.format(SHOT_FORMAT, playerName, nextPin.getCount()));
    }

    public static void printBoard(ResultFrameDtos resultFrameDtos) {
        System.out.println(getHeaderRow());
        resultFrameDtos
                .getResultFrameDto()
                .forEach(dto -> {
                    System.out.println(getFramesRow(dto));
                    System.out.println(generateScoreRow(dto));
                });
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
                .getSymbolStrings()
                .forEach(s -> sb.append(String.format(RESULT_FORMAT, s)));
    }

    private static void fillEmpty(ResultFrameDto resultFrameDto, StringBuilder sb) {
        for (int round = resultFrameDto.getLastFrame(); round < Frame.FINAL_FRAME; round++) {
            sb.append(String.format(RESULT_FORMAT, ""));
        }
    }

    private static String generateScoreRow(ResultFrameDto resultFrameDto) {
        StringBuilder sb = new StringBuilder(String.format(BOARD_HEADER_FIRST_COLUMN, ""));
        resultFrameDto
                .getScoreStrings()
                .forEach(score -> sb.append(String.format(RESULT_FORMAT, score)));
        fillEmpty(resultFrameDto, sb);
        return sb.toString();
    }
}
