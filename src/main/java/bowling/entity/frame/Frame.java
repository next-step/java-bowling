package bowling.entity.frame;

import bowling.entity.Pin;

public interface Frame {
    int frameNo();

    Frame pinResult(Pin fallenPin);

    boolean isFrameEnd();

    String scoreResult();
}
