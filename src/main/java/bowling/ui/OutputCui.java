package bowling.ui;

import static java.lang.System.out;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Score;
import java.util.Optional;

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
        out.print("\n|      |");
        int accumulatedScore = 0;
        while (frame != null) {
            accumulatedScore = drawEachScore(accumulatedScore, frame);
            frame = frame.next();
        }
    }

    private static int drawEachScore(int accumulatedScore, Frame frame) {
        Optional<Integer> score = getScore(frame);
        if (score.isPresent()) {
            accumulatedScore += score.get();
            out.print(String.format("%4d", accumulatedScore) + "  |");
            return accumulatedScore;
        }
        out.print("      |");
        return accumulatedScore;
    }

    private static Optional<Integer> getScore(Frame frame) {
        return frame.scoreCalculated();
    }

    private static String drawFirstScore(Optional<Score> firstScore) {
        if (firstScore.isEmpty()) {
            return GAP;
        }

        Score first = firstScore.orElseThrow();

        if (first.get() == 0) {
            return GUTTER_SYMBOL;
        }
        
        if (first.get() == 10) {
            return STRIKE_SYMBOL;
        }
        return Integer.toString(first.get());
    }

    private static String drawSecondScore(Optional<Score> firstScore, Optional<Score> secondScore) {
        if (secondScore.isEmpty()) {
            return GAP;
        }

        Score first = firstScore.orElseThrow();
        Score second = secondScore.orElseThrow();

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

    private static String drawExtraScore(Optional<Score> secondScore, Optional<Score> extraScore) {
        if (extraScore.isEmpty()) {
            return GAP;
        }

        Score second = secondScore.orElseThrow();
        Score extra = extraScore.orElseThrow();

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
