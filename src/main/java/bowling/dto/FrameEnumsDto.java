package bowling.dto;

import java.util.List;

public class FrameEnumsDto {
    private final List<FrameEnumDto> frameEnums;

    public FrameEnumsDto(List<FrameEnumDto> frameEnums) {
        this.frameEnums = frameEnums;
    }

    public List<FrameEnumDto> getFrameEnums() {
        return frameEnums;
    }
}
