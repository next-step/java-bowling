package bowling.ui;

import static java.lang.System.out;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Score;
import java.util.Objects;

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
        out.printf(fixedLengthString("  %s", 3), drawFirstScore(frame.getFirstScore()));
        out.print(fixedLengthString(drawSecondScore(frame.getFirstScore(), frame.getSecondScore()), 2));
        out.print(GAP + SCORE_SEPARATOR);
    }

    private static void drawFinalFrame(Frame finalFrame) {
        out.printf(fixedLengthString("  %s", 3), drawFirstScore(finalFrame.getFirstScore()));
        out.print(fixedLengthString(drawSecondScore(finalFrame.getFirstScore(), finalFrame.getSecondScore()), 1));
        out.print(fixedLengthString(drawExtraScore(finalFrame.getSecondScore(), ((FinalFrame)finalFrame).getExtraScore()), 1));
        out.print(GAP + SCORE_SEPARATOR);
    }

    private static String drawFirstScore(Score score) {
        if (Objects.isNull(score)) {
            return GAP;
        }

        if (score.get() == 0) {
            return GUTTER_SYMBOL;
        }
        
        if (score.get() == 10) {
            return STRIKE_SYMBOL;
        }
        return Integer.toString(score.get());
    }

    private static String drawSecondScore(Score firstScore, Score secondScore) {
        if (Objects.isNull(secondScore)) {
            return GAP;
        }

        if (firstScore.get() == 10 && secondScore.get() == 10) {
            return SCORE_SEPARATOR + STRIKE_SYMBOL;
        }

        if (firstScore.get() != 10 && firstScore.get() + secondScore.get() == 10) {
            return SCORE_SEPARATOR + SPARE_SYMBOL;
        }

        if (firstScore.get() != 10 && secondScore.get() == 0) {
            return SCORE_SEPARATOR + MISS_SYMBOL;
        }

        return SCORE_SEPARATOR + secondScore.get();
    }

    private static String drawExtraScore(Score secondScore, Score extraScore) {
        if (Objects.isNull(extraScore)) {
            return GAP;
        }

        if (extraScore.get() == 10) {
            return STRIKE_SYMBOL;
        }

        if (secondScore.get() + extraScore.get() == 10) {
            return SPARE_SYMBOL;
        }

        return Integer.toString(extraScore.get());
    }

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
