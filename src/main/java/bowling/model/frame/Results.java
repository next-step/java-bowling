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

    public static Results of(List<FrameResult> frameResult) {
        for (int i = 1; i < frameResult.size(); i++) {
            int score = frameResult.get(i).getScore();
            if (score > -1) {
                frameResult.get(i).calculate(frameResult.get(i - 1));
            }
        }
        return new Results(frameResult);
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
