package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FrameResults {

    private static final int BOWLING_GAME_FRAME_COUNT = 10;

    private List<FrameResult> frameResults;

    private FrameResults() {
    }

    public static FrameResults init() {
        List<FrameResult> frameResults = new ArrayList<>();
        IntStream.rangeClosed(1, BOWLING_GAME_FRAME_COUNT).forEach(i -> {
            FrameNumber frameNumber = FrameNumber.of(i);

        });
    }

    public void addToFrameResults(FrameResult frameResult) {
        frameResults.add(frameResult);
    }
}
