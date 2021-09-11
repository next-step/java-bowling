package bowling.view;

import bowling.domain.Frames;
import bowling.domain.PitchResult;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.Scores;

import java.util.stream.Collectors;

public class ResultView {

    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMITER = "|";
    private static final String SPACING_WORD = " ";
    private static final String SYMBOL_STRIKE = "X";
    private static final String SYMBOL_SPARE = "/";
    private static final String SYMBOL_GUTTER = "-";
    private static final String SYMBOL_UNSCORED = "";
    private static final String BLANK_SCORE = "      ";
    private static final int MAX_WORD_LENGTH = 6;

    private ResultView() {
    }

    public static void header() {
        System.out.println(HEADER);
    }

    public static void frames(Player player, Frames frames) {
        playerName(player);
        frameResults(frames);
        blankLine();
    }

    public static void scores(Scores scores) {
        blankScore();
        frameScores(scores);
        blankLine();
        blankLine();
    }

    private static void playerName(Player player) {
        System.out.printf("%s%s%s", DELIMITER, processWordLength(player.name()), DELIMITER);
    }

    private static void frameResults(Frames frames) {
        frames.frames().stream()
            .map(frame -> frame.results().stream()
                    .map(ResultView::resultToSymbol)
                    .collect(Collectors.joining(DELIMITER)))
            .forEach(result -> System.out.print(String.format("%s%s", processWordLength(result), DELIMITER)));
    }

    private static void blankScore() {
        System.out.printf("%s%s%s", DELIMITER, BLANK_SCORE, DELIMITER);
    }

    private static void frameScores(Scores scores) {
        scores.scores()
            .forEach(score -> System.out.printf("%s%s", processWordLength(scoreToSymbol(score)), DELIMITER));
    }

    private static String resultToSymbol(PitchResult result) {
        if (result.isStrike()) {
            return SYMBOL_STRIKE;
        }
        if (result.isSpare()) {
            return SYMBOL_SPARE;
        }
        if (result.isGutter()) {
            return SYMBOL_GUTTER;
        }
        return String.valueOf(result.fallenPins());
    }

    private static String scoreToSymbol(Score score) {
        if (score.isUnscored()) {
            return SYMBOL_UNSCORED;
        }
        return String.valueOf(score.value());
    }

    private static String processWordLength(String word) {
        int wordLength = word.length();
        int frontSpaces = (MAX_WORD_LENGTH - wordLength) / 2;
        int backSpaces = MAX_WORD_LENGTH - wordLength - frontSpaces;

        StringBuilder builder = new StringBuilder();
        appendSpacingWords(builder, frontSpaces);
        builder.append(word);
        appendSpacingWords(builder, backSpaces);
        return builder.toString();
    }

    private static void appendSpacingWords(StringBuilder builder, int numberOfSpacingWords) {
        for (int i = 0; i < numberOfSpacingWords; i++) {
            builder.append(SPACING_WORD);
        }
    }

    private static void blankLine() {
        System.out.println();
    }
}
