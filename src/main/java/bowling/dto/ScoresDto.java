package bowling.dto;

import java.util.List;

public class ScoresDto {
    private final List<ScoreDto> scores;

    public ScoresDto(List<ScoreDto> scores) {
        this.scores = scores;
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
