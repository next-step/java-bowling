package bowling.dto;

import java.util.List;

public class FrameStatusesDto {
    private final List<FrameStatusDto> frameStatuses;

    public FrameStatusesDto(List<FrameStatusDto> frameStatuses) {
        this.frameStatuses = frameStatuses;
    }

    public List<FrameStatusDto> getFrameStatuses() {
        return frameStatuses;
    }
}
