package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Stream;

public class FrameResults {

    private final LinkedList<FrameResult> frameResults;

    private FrameResults(LinkedList<FrameResult> frameResults) {
        this.frameResults = frameResults;
    }

    static FrameResults of(LinkedList<FrameResult> frameResults) {
        return new FrameResults(frameResults);
    }

    public static FrameResults createFrameResultsByFirstFrame(Frame frame) {
        LinkedList<FrameResult> results = new LinkedList<>();

        FrameResult frameResult = frame.createFrameResult();
        frameResult.addTotalScore(0);
        results.add(frameResult);

        addFrameResult(frame, results);
        return new FrameResults(results);
    }

    private static void addFrameResult(Frame frame, LinkedList<FrameResult> results) {
        while (frame.round() < FINAL_ROUND && frame.nextFrame().isPresent()) {
            frame = frame.nextFrame().get();

            FrameResult frameResult = frame.createFrameResult();
            frameResult.addTotalScore(results.getLast().totalScore());
            results.add(frameResult);
        }
    }

    public Stream<FrameResult> values() {
        return frameResults.stream();
    }

    public int size() {
        return frameResults.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResults that = (FrameResults) o;
        return Objects.equals(frameResults, that.frameResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResults);
    }

}
