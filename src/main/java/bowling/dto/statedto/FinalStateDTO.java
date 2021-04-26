package bowling.dto.statedto;

import java.util.List;

public class FinalStateDTO extends StateDTO {
    private List<StateDTO> stateDTOList;

    public FinalStateDTO(String state, List<StateDTO> stateDTOList) {
        super(state, null);
        this.stateDTOList = stateDTOList;
    }

    @Override
    public String state() {
        return super.state();
    }

    @Override
    public List<Integer> pins() {
        return super.pins();
    }

    public List<StateDTO> stateDTOList() {
        return stateDTOList;
    }
}
