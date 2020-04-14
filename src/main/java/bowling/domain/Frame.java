package bowling.domain;

import bowling.dto.FrameDto;

public interface Frame {
    FrameDto getDto();

    boolean isFrameClosed();

    void shot(int shot);

    Frame next(int shot);
}
