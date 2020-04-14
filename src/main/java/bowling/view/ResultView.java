package bowling.view;

import bowling.domain.Game;
import bowling.domain.frame.Pitch;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultView {
    private static final String NAME_TITLE = "| NAME | ";
    private static final String NAME_VALUE = "| %4s | ";
    private static final String EMPTY = "|      | ";
    private static final String DELIMITER = " | ";
    private static final String FRAME_FORMAT = " %02d ";
    private static final String PIN_COUNT_FORMAT = "%-4s";
    private static final String EMPTY_SCORE = "     | ";
    private static final String PIN_DELIMITER = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    public static void displayGameBoard(Game game) {
        displayHeader(game.getFrameTotal());
        displayBody(game);
        displayScores(game);
    }

    private static void displayHeader(int maxFrame) {
        System.out.print(NAME_TITLE);

        for (int i = 1; i <= maxFrame; i++) {
            System.out.print(String.format(FRAME_FORMAT, i) + DELIMITER);
        }
        System.out.println();
    }

    private static void displayBody(Game game) {
        System.out.print(String.format(NAME_VALUE, game.getPlayerName()));

        for (int i = 0, maxFrame = game.getFrameTotal(); i < maxFrame; i++) {
            displayPinCounts(game.getFramePinCounts(i));
        }
        System.out.println();
    }

    private static void displayPinCounts(List<Pitch> pitches) {
        String stringPinCounts = pitches.stream()
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

    private static void displayScores(Game game) {
        System.out.print(EMPTY);
        for (int i = 0, maxFrame = game.getFrameTotal(); i < maxFrame; i++) {
            Optional<Integer> score = game.getFrameScore(i);
            displayScore(score);
        }
        System.out.println();
    }

    private static void displayScore(Optional<Integer> score) {
        if (score.isPresent()) {
            System.out.print(String.format(FRAME_FORMAT, score.get()) +
                    DELIMITER);
            return;
        }
        System.out.print(EMPTY_SCORE);
    }

    private ResultView() {
    }
}
