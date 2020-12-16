package bowling.domain.frames;

import java.util.stream.Stream;

public interface FramesViewDto {
    Stream<FrameViewDto> viewDtoStream();
}
