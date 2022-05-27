package bowling.ui;

import static java.lang.System.out;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Score;
import java.util.Objects;
import java.util.Optional;

public final class OutputCui {
    private static final String SCORE_SEPARATOR = "|";
    private static final String STRIKE_SYMBOL = "X";
    private static final String SPARE_SYMBOL = "/";
    private static final String MISS_SYMBOL = "-";
    private static final String GUTTER_SYMBOL = "-";
    private static final String GAP = " ";

    private OutputCui() { }

    public static void drawStatus(Game game) {
        String name = game.name();
        Frame current = game.frames().head();

        drawUpperLine(current);
        out.printf(fixedLengthString("|  %s |", 7), name);

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
            out.printf(fixedLengthString("  %s  |", 7), String.format("%02d", number++));
            frame = frame.next();
        }
        out.println();
    }

    private static void drawFrame(Frame frame) {
        out.printf(fixedLengthString("  %s", 3),
            drawFirstScore(frame.getFirstScoreAsOptional()));
        out.print(fixedLengthString(drawSecondScore(frame.getFirstScoreAsOptional(),
            frame.getSecondScoreAsOptional()), 2));
        out.print(GAP + SCORE_SEPARATOR);
    }

    private static void drawFinalFrame(Frame finalFrame) {
        out.printf(fixedLengthString("  %s", 3),
            drawFirstScore(finalFrame.getFirstScoreAsOptional()));
        out.print(fixedLengthString(drawSecondScore(finalFrame.getFirstScoreAsOptional(),
            finalFrame.getSecondScoreAsOptional()), 1));
        out.print(fixedLengthString(drawExtraScore(finalFrame.getSecondScoreAsOptional(),
            ((FinalFrame)finalFrame).getExtraScore()), 1));
        out.print(GAP + SCORE_SEPARATOR);
    }

    private static void drawScoreLine(Frame frame) {
        out.println();
        out.printf("|      |");
        while (frame != null) {
            out.printf(fixedLengthString("  %s  |", 8), getScoreString(frame));
            frame = frame.next();
        }
    }

    private static String getScoreString(Frame frame) {
        Optional<Integer> score = frame.scoreCalculated();
        return score.map(Object::toString)
            .orElse(GAP);
    }

    private static String drawFirstScore(Optional<Score> score) {
        if (score.isEmpty()) {
            return GAP;
        }

        if (score.get().get() == 0) {
            return GUTTER_SYMBOL;
        }
        
        if (score.get().get() == 10) {
            return STRIKE_SYMBOL;
        }
        return Integer.toString(score.get().get());
    }

    private static String drawSecondScore(Optional<Score> firstScore, Optional<Score> secondScore) {
        if (secondScore.isEmpty()) {
            return GAP;
        }

        if (firstScore.get().get() == 10 && secondScore.get().get() == 10) {
            return SCORE_SEPARATOR + STRIKE_SYMBOL;
        }

        if (firstScore.get().get() != 10 && firstScore.get().get() + secondScore.get().get() == 10) {
            return SCORE_SEPARATOR + SPARE_SYMBOL;
        }

        if (firstScore.get().get() != 10 && secondScore.get().get() == 0) {
            return SCORE_SEPARATOR + MISS_SYMBOL;
        }

        return SCORE_SEPARATOR + secondScore.get().get();
    }

    private static String drawExtraScore(Optional<Score> secondScore, Optional<Score> extraScore) {
        if (extraScore.isEmpty()) {
            return GAP;
        }

        if (extraScore.get().get() == 10) {
            return STRIKE_SYMBOL;
        }

        if (secondScore.get().get() + extraScore.get().get() == 10) {
            return SPARE_SYMBOL;
        }

        return Integer.toString(extraScore.get().get());
    }

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
