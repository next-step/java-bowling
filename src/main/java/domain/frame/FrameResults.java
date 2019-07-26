package domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Score.UNFINISHED_SCORE;

public class FrameResults {

    private List<FrameResult> frameResults;

    private FrameResults(List<FrameResult> frameResults) {
        this.frameResults = new ArrayList<>(frameResults);
    }

    public static FrameResults from(List<FrameResult> frameResults) {
        return frameResults.stream()
                .reduce((previous, current) -> current.updateResult(previous))
                .map(frameResult -> new FrameResults(frameResults))
                .get();
    }

    public List<String> getFinalStates() {
        return frameResults.stream()
                .map(FrameResult::getState)
                .collect(Collectors.toList());
    }

    public List<Integer> getFinalScores() {
        return frameResults.stream()
                .map(FrameResult::getScore)
                .filter(score -> score != UNFINISHED_SCORE)
                .collect(Collectors.toList());
    }

    public List<FrameResult> getFrameResults() {
        return Collections.unmodifiableList(frameResults);
    }
}
