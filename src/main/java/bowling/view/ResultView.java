package bowling.view;

import bowling.domain.Frames;

public class ResultView {
    private static final String FRAME = "|";
    private static final String FRAME_TITLE = "NAME | %S ";
    private static final String FRAME_BODY = "%s | ";
    private static final String SEPARATOR = System.lineSeparator();

    public static void printBowlResult(Frames frames) {
        frames.getFrames();
    }
}
