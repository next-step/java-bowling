package bowling.domain.frame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Results {
    private static final int READY_FRAME = 0;

    private int totalScore = 0;
    private List<Result> results;

    public Results(LinkedList<Frame> frames) {
        this.results = calculateFrames(frames);
    }

    private List<Result> calculateFrames(LinkedList<Frame> frames) {
        LinkedList<Frame> framesBeforeCalculation = new LinkedList<>(frames);
        List<Result> results = new ArrayList<>();

        while (framesBeforeCalculation.size() != READY_FRAME) {
            Frame frame = calculate(framesBeforeCalculation);
            Result result = frame.getFrameResult();
            if (result.isCalculation()) {
                totalScore = result.totalScore(totalScore);
            }
            results.add(result);
        }
        return results;
    }

    private Frame calculate(LinkedList<Frame> frames) {
        Frame first = frames.removeFirst();
        for (Frame frame : frames) {
            first.calculateByBeforeScore(frame.getState());
        }
        return first;
    }

    public List<Result> getResults() {
        return new ArrayList<>(results);
    }

    public int getTotalScore() {
        return totalScore;
    }
}
