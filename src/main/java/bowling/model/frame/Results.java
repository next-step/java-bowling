package bowling.model.frame;

import java.util.Collections;
import java.util.List;

import static bowling.model.frame.state.Score.DEFAULT_SCORE;
import static java.util.stream.Collectors.toList;

public class Results {

    private static final int DEFAULT_INDEX = 1;
    private List<FrameResult> resultsOfEachFrame;

    private Results(List<FrameResult> resultsOfEachFrame) {
        this.resultsOfEachFrame = resultsOfEachFrame;
    }

    public static Results of(List<FrameResult> frameResults) {
        for (int i = DEFAULT_INDEX; i < frameResults.size(); i++) {
            FrameResult frameResult = frameResults.get(i);
            FrameResult prevFrameResult = frameResults.get(i - DEFAULT_INDEX);
            frameResult.calculate(prevFrameResult);
        }
        return new Results(frameResults);
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
