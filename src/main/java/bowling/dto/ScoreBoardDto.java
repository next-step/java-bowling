package bowling.dto;

import java.util.Map;

public class ScoreBoardDto {
    private final Map<PlayerDto, RollsAndBoardDto> scoreBoard;

    public ScoreBoardDto(Map<PlayerDto, RollsAndBoardDto> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Map<PlayerDto, RollsAndBoardDto> getScoreBoard() {
        return scoreBoard;
    }
}
