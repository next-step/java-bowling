package bowling.domain.frame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Results {
    private static final int READY_FRAME = 0;

    private int totalScore = 0;
    private List<Result> results;

    public Results(LinkedList<Frame> frames) {
        this.results = createResults(calculateFrames(frames));
    }

    private List<Result> createResults(LinkedList<Frame> frames) {
        List<Result> results = new ArrayList<>();
        for (Frame frame : frames) {
            Result result = frame.getFrameResult();
            if (result.isCalculation()) {
                totalScore = result.totalScore(totalScore);
            }
            results.add(result);
        }
        return results;
    }

    private LinkedList<Frame> calculateFrames(LinkedList<Frame> frames) {
        LinkedList<Frame> framesBeforeCalculation = new LinkedList<>(frames);
        LinkedList<Frame> framesCalculation = new LinkedList<>();
        while (framesBeforeCalculation.size() != READY_FRAME) {
            framesCalculation.add(calculate(framesBeforeCalculation));
        }
        return framesCalculation;
    }

    private Frame calculate(LinkedList<Frame> frames) {
        Frame first = frames.removeFirst();
        for (Frame frame : frames) {
            if (Objects.nonNull(first.getState().getScore()) && !first.getState().getScore().isCalculation()) {
                Score score = frame.calculateByBeforeScore(first.getState().getScore());
                first.updateScore(score);
            }
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
