package bowling.ui;

import static java.lang.System.out;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Score;
import java.util.Optional;
import java.util.OptionalInt;

public final class OutputCui {
    private static final String SCORE_SEPARATOR = "|";
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";
    private static final String MISS_SYMBOL = "-";
    private static final String GUTTER_SYMBOL = "-";
    private static final String GAP = " ";
    private static final int FRAME_WIDTH = 7;

    private OutputCui() { }

    public static void drawStatus(Game game) {
        String name = game.name();
        Frame current = game.frames().head();

        drawUpperLine(current);
        out.printf(fixedLengthString("|  %s |", FRAME_WIDTH), name);

        while (current.next() != null) {
            drawFrame(current);
            current = current.next();
        }
        drawFinalFrame(current);
        drawScoreLine(game.frames().head());
        out.println();
    }

    private static void drawUpperLine(Frame frame) {
        out.print("| NAME |");
        int number = 1;
        while (frame != null) {
            out.printf(fixedLengthString("  %s  |", FRAME_WIDTH), String.format("%02d", number++));
            frame = frame.next();
        }
        out.println();
    }

    private static void drawFrame(Frame frame) {
        final Optional<Score> firstScore = frame.getFirstScoreAsOptional();
        final Optional<Score> secondScore = frame.getSecondScoreAsOptional();
        firstScore.ifPresentOrElse(
            score -> out.printf(fixedLengthString("  %s", 3),
                drawFirstScore(score)),
            () -> out.printf(fixedLengthString("  %s", 3), GAP)
        );
        secondScore.ifPresentOrElse(score ->
            out.print(fixedLengthString(drawSecondScore(firstScore.get(), score), 2)),
            () -> out.printf(GAP + GAP)
        );
        out.print(GAP + SCORE_SEPARATOR);
    }

    private static void drawFinalFrame(Frame finalFrame) {
        final Optional<Score> firstScore = finalFrame.getFirstScoreAsOptional();
        final Optional<Score> secondScore = finalFrame.getSecondScoreAsOptional();
        final Optional<Score> extraScore = ((FinalFrame) finalFrame).getExtraScore();
        firstScore.ifPresentOrElse(
            score -> out.printf(fixedLengthString("  %s", 3), drawFirstScore(score)),
            () -> out.printf(fixedLengthString("  %s", 3), GAP)
        );
        secondScore.ifPresentOrElse(score ->
                out.print(fixedLengthString(drawSecondScore(firstScore.get(), score), 1)),
            () -> out.printf(GAP + GAP)
        );
        extraScore.ifPresentOrElse(extra ->
                out.print(fixedLengthString(drawExtraScore(secondScore.get(), extra), 1)),
            () -> out.printf(GAP)
        );
        out.print(SCORE_SEPARATOR);
    }

    private static void drawScoreLine(Frame frame) {
        out.print(System.lineSeparator() + "|      |");
        int accumulatedScore = 0;
        while (frame != null) {
            accumulatedScore = drawEachScore(accumulatedScore, frame);
            frame = frame.next();
        }
    }

    private static int drawEachScore(int accumulatedScore, Frame frame) {
        OptionalInt score = getScore(frame);
        if (score.isPresent()) {
            accumulatedScore += score.getAsInt();
            out.print(String.format("%4d", accumulatedScore) + "  |");
            return accumulatedScore;
        }
        out.print("      |");
        return accumulatedScore;
    }

    private static OptionalInt getScore(Frame frame) {
        return frame.scoreCalculated();
    }

    private static String drawFirstScore(Score first) {
        if (first.get() == 0) {
            return GUTTER_SYMBOL;
        }
        
        if (first.get() == 10) {
            return STRIKE_SYMBOL;
        }
        return Integer.toString(first.get());
    }

    private static String drawSecondScore(Score first, Score second) {
        if (first.get() == 10 && second.get() == 10) {
            return SCORE_SEPARATOR + STRIKE_SYMBOL;
        }

        if (first.get() != 10 && first.get() + second.get() == 10) {
            return SCORE_SEPARATOR + SPARE_SYMBOL;
        }

        if (first.get() != 10 && second.get() == 0) {
            return SCORE_SEPARATOR + MISS_SYMBOL;
        }

        return SCORE_SEPARATOR + second.get();
    }

    private static String drawExtraScore(Score second, Score extra) {
        if (extra.get() == 10) {
            return STRIKE_SYMBOL;
        }

        if (second.get() + extra.get() == 10) {
            return SPARE_SYMBOL;
        }

        return Integer.toString(extra.get());
    }

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
