package bowling.presentation.output.util;

import bowling.domain.frame.Frames;
import bowling.presentation.output.ScoresFrame;

public class FramesOutputHelper{

    private static final int WHOLE_FRAME_SIZE = 10;

    private static final String EMPTY_FRAME = "       |";

    private FramesOutputHelper() {
    }

    public static FramesOutputHelper create() {
        return new FramesOutputHelper();
    }

    public String output(Frames frames) {
        StringBuilder output = new StringBuilder();

        output.append(
                frames.getAll().stream()
                        .map(frame -> ScoresFrame.from(frame.getAllScores()).toOutput())
                        .reduce("", String::concat)
        );
        for (int i = frames.size(); i < WHOLE_FRAME_SIZE; i++) {
            output.append(EMPTY_FRAME);
        }

        return output.toString();
    }

}
