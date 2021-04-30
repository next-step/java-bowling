package bowling.dto;

import bowling.domain.engine.frame.Frames;

import java.util.List;

public class FramesDto {

    private final List<FrameStateDto> states;
    private final List<ScoreDto> scores;

    private FramesDto(List<FrameStateDto> states, List<ScoreDto> scores) {
        this.states = states;
        this.scores = scores;
    }

    public static FramesDto of(Frames frames) {
        List<ScoreDto> scoreDtoList = frames.exportScores().getScores();

        return new FramesDto(frames.exportFrameStates(), scoreDtoList);
    }

    public List<FrameStateDto> getStates() {
        return states;
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
