package bowling.dto;

import java.util.Map;

public class ScoreBoardDto {
    private final Map<PlayerDto, PlayerStatusDto> scoreBoard;

    public ScoreBoardDto(Map<PlayerDto, PlayerStatusDto> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Map<PlayerDto, PlayerStatusDto> getScoreBoard() {
        return scoreBoard;
    }
}
