package bowling.dto;

import bowling.dto.statedto.StateDTO;

public class FrameDTO {
    private final StateDTO stateDTO;

    public FrameDTO(StateDTO stateDTO) {
        this.stateDTO = stateDTO;
    }

    public StateDTO stateDTO(){
        return stateDTO;
    }
}
