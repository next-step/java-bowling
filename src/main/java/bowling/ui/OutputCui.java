package bowling.ui;

import static java.lang.System.out;

import bowling.domain.Frame;
import bowling.domain.Frames;
import java.util.List;

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
        out.println();

    }

    private void drawUpperLine(int size) {
        out.print("| NAME |");
        for (int i = 1; i <= size; ++i) {
            out.printf(fixedLengthString("  %d  |", 7), i);
        }
        out.println();
    }

    private void drawFrame(Frame frame) {
        out.print("  " + frame.getFirstScore().get() + "|" + frame.getSecondScore().get() + " |");
    }

    private static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

}
