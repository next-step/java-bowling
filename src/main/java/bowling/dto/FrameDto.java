package bowling.dto;

import java.util.List;

public class FrameDto {
    private final List<ShotScoreDto> shotScores;
    private final Integer score;

    public FrameDto(List<ShotScoreDto> shotScores, Integer score) {
        this.shotScores = shotScores;
        this.score = score;
    }

    public List<ShotScoreDto> getShotScores() {
        return shotScores;
    }

    public Integer getScore() {
        return score;
    }
}
