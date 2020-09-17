package bowling.domain.frame.dto;

import java.util.List;

public class ScoreBoardDTO {

    private final List<FrameDTO> scoreBoardDTO;

    public ScoreBoardDTO(List<FrameDTO> scoreBoardDTO) {
        this.scoreBoardDTO = scoreBoardDTO;
    }

    public List<FrameDTO> getScoreBoardDTO() {
        return scoreBoardDTO;
    }
}
