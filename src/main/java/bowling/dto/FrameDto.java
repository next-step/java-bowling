package bowling.dto;

import java.util.List;
import java.util.Optional;

public class FrameDto {
    private final List<ShotScoreDto> shotScores;
    private final Optional<Integer> Score;

    public FrameDto(List<ShotScoreDto> shotScores, Optional<Integer> score) {
        this.shotScores = shotScores;
        Score = score;
    }

    public List<ShotScoreDto> getShotScores() {
        return shotScores;
    }

    public Optional<Integer> getScore() {
        return Score;
    }
}
