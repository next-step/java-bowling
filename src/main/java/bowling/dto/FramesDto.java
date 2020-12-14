package bowling.dto;

public class FramesDto {
    private final PinsDto pinsDto;
    private final FrameEnumsDto frameEnumsDto;

    public FramesDto(PinsDto pinsDto, FrameEnumsDto frameEnumsDto) {
        this.pinsDto = pinsDto;
        this.frameEnumsDto = frameEnumsDto;
    }

    public PinsDto getPinsDto() {
        return pinsDto;
    }

    public FrameEnumsDto getFrameEnumsDto() {
        return frameEnumsDto;
    }
}
