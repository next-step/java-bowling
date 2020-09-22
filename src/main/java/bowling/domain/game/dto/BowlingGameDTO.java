package bowling.domain.game.dto;

import bowling.domain.frame.dto.ScoreBoardDTO;

import java.util.List;

public class BowlingGameDTO {

    private final List<ScoreBoardDTO> scoreBoardDTOs;

    public BowlingGameDTO(List<ScoreBoardDTO> scoreBoardDTOs) {
        this.scoreBoardDTOs = scoreBoardDTOs;
    }

    public List<ScoreBoardDTO> getScoreBoardDTOs() {
        return scoreBoardDTOs;
    }
}
