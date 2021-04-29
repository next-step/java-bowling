package bowling.dto;

import java.util.List;

public class FrameDTO {
    private final List<StateDTO> stateDTOList;
    private final ScoreDTO scoreDTO;

    public FrameDTO(List<StateDTO> stateDTOList, ScoreDTO scoreDTO) {
        this.stateDTOList = stateDTOList;
        this.scoreDTO = scoreDTO;
    }

    public List<StateDTO> stateDTOList(){
        return stateDTOList;
    }

    public ScoreDTO scoreDTO() {
        return scoreDTO;
    }
}
