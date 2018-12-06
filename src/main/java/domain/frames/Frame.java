package domain.frames;

import domain.status.Status;

public interface Frame {

    Frame bowling(int ball);

    int getFrameNumber();

    Status collectResult();
}
