package bowling.model.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<FrameResult> frameResults;
    private int totalScore = 0;

    public Board() {
        frameResults = new ArrayList<>();
    }

    void add(FrameResult result) {
        if (!result.isUnScore()) {
            totalScore = result.addTotalScore(totalScore);
        }
        frameResults.add(result);
    }

    public List<String> getStateResult() {
        return frameResults.stream()
                            .map(FrameResult::getDesc)
                            .filter(frameResults -> !frameResults.equals(""))
                            .collect(Collectors.toList());
    }

    public List<Integer> getScoreResult() {
        return frameResults.stream()
                            .map(FrameResult::getTotalScore)
                            .filter(frameResults -> frameResults != -1)
                            .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return "Board [frameResults=" + frameResults + "]";
    }

}
