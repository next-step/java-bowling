package bowling.dto;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameResults {

    private List<FrameResult> frameResults = new ArrayList<>();

    public FrameResults(List<Frame> frames) {
        frames.forEach(frame -> frameResults.add(new FrameResult(frame)));
    }

    public List<FrameResult> results() {
        return frameResults;
    }


    public List<String> allStates() {
       return frameResults.stream()
                .map(FrameResult::state)
                .collect(Collectors.toList());
    }

    public List<ScoreDto> allScores(){
        return frameResults.stream()
                .map(FrameResult::score)
                .collect(Collectors.toList());
    }


}
