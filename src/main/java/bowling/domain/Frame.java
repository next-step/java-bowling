package bowling.domain;

import bowling.domain.frame.info.FrameInfo;

public interface Frame {

    Frame roll(int downPins);

    FrameInfo frameInfo();

    Status pinStatus();

    int numberOfDownedPins();

    boolean hasNextRound();
    
}
