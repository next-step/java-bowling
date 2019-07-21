package bowling.model.frame;

import java.util.Collections;
import java.util.List;

import static bowling.model.frame.state.Score.DEFAULT_SCORE;
import static java.util.stream.Collectors.toList;

public class Results {

    private List<FrameResult> resultsOfEachFrame;

    private Results(List<FrameResult> resultsOfEachFrame) {
        this.resultsOfEachFrame = resultsOfEachFrame;
    }

    public static Results of(List<FrameResult> frameResults) {
        return frameResults.stream()
                .reduce((prev, current) -> current.calculate(prev))
                .map(frameResult -> new Results(frameResults))
                .orElseThrow(IllegalStateException::new);
    }

    public List<Integer> getScores() {
        return resultsOfEachFrame.stream()
                .map(FrameResult::getScore)
                .filter(score -> DEFAULT_SCORE != score)
                .collect(toList());
    }

    public List<String> getStates() {
        return resultsOfEachFrame.stream()
                .map(FrameResult::getState)
                .collect(toList());
    }

    List<FrameResult> getResults() {
        return Collections.unmodifiableList(resultsOfEachFrame);
    }
}