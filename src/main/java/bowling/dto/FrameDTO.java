package bowling.dto;

import java.util.List;

public class FrameDTO {
    private final List<StateDTO> stateDTOList;
    private final int score;

    public FrameDTO(List<StateDTO> stateDTOList, int score) {
        this.stateDTOList = stateDTOList;
        this.score = score;
    }

    public List<StateDTO> stateDTOList(){
        return stateDTOList;
    }

    public int score() {
        return score;
    }
}
