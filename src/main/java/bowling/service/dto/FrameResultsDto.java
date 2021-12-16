package bowling.service.dto;

import bowling.domain.result.FrameResult;
import bowling.domain.result.FrameResults;

import java.util.List;

public class FrameResultsDto {
    private final List<String> frameDescriptions;
    private final List<Integer> scores;

    private FrameResultsDto(List<String> frameDescriptions, List<Integer> scores) {
        this.frameDescriptions = frameDescriptions;
        this.scores = scores;
    }

    public static FrameResultsDto of(FrameResults frameResults) {
        List<FrameResult> values = frameResults.getValues();
//        values.stream()
//                .map(FrameResult::getStates)
//                .map()
        return null;
    }

    public List<String> getFrameDescriptions() {
        return frameDescriptions;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
