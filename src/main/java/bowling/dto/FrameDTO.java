package bowling.dto;

import java.util.List;

public class FrameDTO {
    private final List<StateDTO> stateDTOList;

    public FrameDTO(List<StateDTO> stateDTOList) {
        this.stateDTOList = stateDTOList;
    }

    public List<StateDTO> stateDTOList(){
        return stateDTOList;
    }
}
