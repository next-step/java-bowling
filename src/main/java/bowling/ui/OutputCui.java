package bowling.ui;

import static java.lang.System.out;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Score;
import java.util.List;
import java.util.Objects;

public class OutputCui {

    public void drawFrames(Frames frames) {
        String name = frames.getName();
        Frame current = frames.getHead();
        List<Frame> frameList = frames.get();

        drawUpperLine(frameList.size());
        out.printf(fixedLengthString("|  %s |", 7), name);

        while (current.next() != null) {
            drawFrame(current);
            current = current.next();
        }
        drawFrame(current);
        out.println();

    }

    private void drawUpperLine(int size) {
        out.print("| NAME |");
        for (int i = 1; i <= size; ++i) {
            out.printf(fixedLengthString("  %s  |", 7), String.format("%02d", i));
        }
        out.println();
    }

    private void drawFrame(Frame frame) {
        out.printf(fixedLengthString("  %s", 3), drawFirstScore(frame.getFirstScore()));
        out.print(fixedLengthString(drawSecondScore(frame.getFirstScore(), frame.getSecondScore()), 2));
        out.print(" |");
    }

    private String drawFirstScore(Score score) {
        if (Objects.isNull(score)) {
            return " ";
        }

        if (score.get() == 10) {
            return "X";
        }
        return Integer.toString(score.get());
    }

    private String drawSecondScore(Score firstScore, Score secondScore) {
        if (Objects.isNull(secondScore)) {
            return " ";
        }

        if (firstScore.get() != 10 && firstScore.get() + secondScore.get() == 10) {
            return "|/";
        }

        if (firstScore.get() != 10 && secondScore.get() == 0) {
            return "|-";
        }

        return "|" + secondScore.get();
    }

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
