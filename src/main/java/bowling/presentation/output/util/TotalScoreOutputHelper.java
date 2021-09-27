package bowling.presentation.output.util;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class TotalScoreOutputHelper {

    private static final int WHOLE_FRAME_SIZE = 10;

    private static final String START_FRAME = "|       |";

    private static final String EMPTY_FRAME = "       |";

    private static final String BOUNDARY = "|";

    private static final String SPACE = " ";

    private TotalScoreOutputHelper() {
    }

    public static TotalScoreOutputHelper create() {
        return new TotalScoreOutputHelper();
    }

    public String output(Frames frames) {
        StringBuilder output =
                new StringBuilder(frames.getAll().stream().map(Frame::getTotalScore).map(this::convert).reduce(START_FRAME, String::concat));

        for (int i = frames.size(); i < WHOLE_FRAME_SIZE; i++) {
            output.append(EMPTY_FRAME);
        }

        output.append("\n");

        return output.toString();
    }

    private String convert(Integer totalScore) {
        if (totalScore == -1) {
            return EMPTY_FRAME;
        }
        StringBuilder result = new StringBuilder(SPACE + SPACE + totalScore);
        for (int i = result.length(); i < 7; i++) {
            result.append(SPACE);
        }
        result.append(BOUNDARY);
        return result.toString();
    }
}
