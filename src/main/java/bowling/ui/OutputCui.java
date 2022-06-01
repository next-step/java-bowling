package bowling.ui;

import static java.lang.System.out;

import bowling.domain.Frame;
import bowling.domain.Game;
import java.util.List;
import java.util.OptionalInt;

public final class OutputCui {
    private static final String MISS_SYMBOL = "-";
    private static final String GUTTER_SYMBOL = "-";
    private static final int FRAME_WIDTH = 7;

    private OutputCui() { }

    public static void drawStatus(List<Game> games) {
        drawUpperLine();

        for (int i = 0; i < games.size(); ++i) {
            Game game = games.get(i);
            String name = game.name();
            Frame current = game.frames().head();
            out.printf(fixedLengthString("|  %s |", FRAME_WIDTH), name);

            while (current.next() != null) {
                drawFrame(current);
                current = current.next();
            }
            drawFinalFrame(current);
            drawScoreLine(game.frames().head());
            out.println();
        }
    }

    public static void drawStatus(Game game) {
        drawUpperLine();

        String name = game.name();
        Frame current = game.frames().head();
        out.printf(fixedLengthString("|  %s |", FRAME_WIDTH), name);

        while (current.next() != null) {
            drawFrame(current);
            current = current.next();
        }
        drawFinalFrame(current);
        drawScoreLine(game.frames().head());
        out.println();
    }

    private static void drawUpperLine() {
        out.print("| NAME |");
        int number = 0;
        while (number++ < 10) {
            out.printf(fixedLengthString("  %s  |", FRAME_WIDTH), String.format("%02d", number));
        }
        out.println();
    }

    private static void drawFrame(Frame frame) {
        out.printf("  " + frame.output() + " |");
    }

    private static void drawFinalFrame(Frame finalFrame) {
        out.printf("  " + finalFrame.output() + "|");
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

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
