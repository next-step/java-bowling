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
        addEmptyScoreDto(scoreDtoList);

        return new FramesDto(frames.exportFrameStates(), scoreDtoList);
    }

    private static void addEmptyScoreDto(List<ScoreDto> scoreDtoList) {
        int moreScores = 10 - scoreDtoList.size();
        for(int i = 0; i < moreScores; ++i) {
            scoreDtoList.add(ScoreDto.empty());
        }
    }

    public List<FrameStateDto> getStates() {
        return states;
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
