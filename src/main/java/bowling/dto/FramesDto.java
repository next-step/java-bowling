package bowling.dto;

import java.util.List;
import java.util.stream.Stream;

public class FramesDto {
    private final List<FrameDto> value;

    private FramesDto(List<FrameDto> value) {
        this.value = value;
    }

    public static FramesDto of(List<FrameDto> value) {
        return new FramesDto(value);
    }

    public Stream<FrameDto> viewDtoStream() {
        return value.stream();
    }
}
