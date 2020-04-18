package bowling.dto;

import java.util.List;

public class FrameDto {
    private final List<ShotScoreDto> shotScores;

    public FrameDto(List<ShotScoreDto> shotScores) {
        this.shotScores = shotScores;
    }

    public List<ShotScoreDto> getShotScores() {
        return shotScores;
    }
}
