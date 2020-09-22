package bowling.model.scoreboard;

import bowling.model.Player;
import bowling.model.frame.dto.FramesDto;
import bowling.model.score.Scores;

import java.util.Map;

public class ScoreBoardDto {
    private final Map<Player, FramesDto> frames;
    private final Map<Player, Scores> scores;

    public ScoreBoardDto(Map<Player, FramesDto> frames, Map<Player, Scores> scores) {
        this.frames = frames;
        this.scores = scores;
    }

    public Map<Player, FramesDto> getFrames() {
        return frames;
    }

    public Map<Player, Scores> getScores() {
        return scores;
    }
}
