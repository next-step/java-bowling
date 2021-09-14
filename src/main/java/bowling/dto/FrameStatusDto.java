package bowling.dto;

import java.util.List;

public class FrameStatusDto {

    private final List<List<Integer>> allFramePitchValues;
    private final List<String> scores;

    public FrameStatusDto(final List<List<Integer>> allFramePitchValues, final List<String> scores) {
        this.allFramePitchValues = allFramePitchValues;
        this.scores = scores;
    }

    public List<List<Integer>> getAllFramePitchValues() {
        return allFramePitchValues;
    }

    public List<String> getScores() {
        return scores;
    }
}
