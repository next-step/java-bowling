package bowling.view;

import bowling.controller.dto.FrameStatus;
import bowling.controller.dto.GameStatus;
import bowling.domain.pitch.Pitch;

import java.util.Optional;
import java.util.stream.Collectors;

public class ResultView {
    private static final String NAME_TITLE = "| NAME | ";
    private static final String NAME_VALUE = "| %4s | ";
    private static final String EMPTY = "|      | ";
    private static final String DELIMITER = " | ";
    private static final String FRAME_FORMAT = " %02d ";
    private static final String SCORE_FORMAT = " %3d";
    private static final String PIN_COUNT_FORMAT = "%-4s";
    private static final String EMPTY_SCORE = "     | ";
    private static final String PIN_DELIMITER = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    public static void displayGameBoard(GameStatus gameStatus) {
        displayHeader(gameStatus.getFrameStatusesSize());
        displayBody(gameStatus);
        displayScores(gameStatus);
    }

    private static void displayHeader(int maxFrame) {
        System.out.print(NAME_TITLE);

        for (int i = 1; i <= maxFrame; i++) {
            System.out.print(String.format(FRAME_FORMAT, i) + DELIMITER);
        }
        System.out.println();
    }

    private static void displayBody(GameStatus gameStatus) {
        System.out.print(String.format(NAME_VALUE, gameStatus.getPlayerName()));

        for (int i = 0, size = gameStatus.getFrameStatusesSize(); i < size;
             i++) {
            displayPinCounts(gameStatus.getFrameStatus(i));
        }
        System.out.println();
    }

    private static void displayPinCounts(FrameStatus frameStatus) {
        if (frameStatus.isEmpty()) {
            System.out.print(EMPTY_SCORE);
            return;
        }
        String stringPinCounts = frameStatus.getPitches().stream()
                .map(ResultView::getSymbol)
                .collect(Collectors.joining(PIN_DELIMITER));

        System.out.print(String.format(PIN_COUNT_FORMAT, stringPinCounts) +
                DELIMITER);
    }

    private static String getSymbol(Pitch pitch) {
        if (pitch.isStrike()) {
            return STRIKE;
        } else if (pitch.isSpare()) {
            return SPARE;
        } else if (pitch.isGutter()) {
            return GUTTER;
        }
        return Integer.toString(pitch.getCount());
    }

    private static void displayScores(GameStatus gameStatus) {
        System.out.print(EMPTY);
        for (int i = 0, size = gameStatus.getFrameStatusesSize(); i < size;
             i++) {
            displayScore(gameStatus.getFrameStatus(i));
        }
        System.out.println();
    }

    private static void displayScore(FrameStatus frameStatus) {
        if (frameStatus.isEmpty()) {
            System.out.print(EMPTY_SCORE);
            return;
        }
        Optional<Integer> score = frameStatus.getScore();
        if (score.isPresent()) {
            System.out.print(String.format(SCORE_FORMAT, score.get()) +
                    DELIMITER);
            return;
        }
        System.out.print(EMPTY_SCORE);
    }

    private ResultView() {
    }
}
