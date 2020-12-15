package bowling.dto;

public class FramesDto {
    private final PinsDto pinsDto;
    private final FrameStatusesDto frameStatusesDto;

    public FramesDto(PinsDto pinsDto, FrameStatusesDto frameStatusesDto) {
        this.pinsDto = pinsDto;
        this.frameStatusesDto = frameStatusesDto;
    }

    public PinsDto getPinsDto() {
        return pinsDto;
    }

    public FrameStatusesDto getFrameStatusesDto() {
        return frameStatusesDto;
    }
}
