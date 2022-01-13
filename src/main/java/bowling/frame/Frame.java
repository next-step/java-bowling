package bowling.frame;

import bowling.Pins;

public interface Frame {

    int MIN_FRAME_NO = 1;
    int MAX_FRAME_NO = 10;

    Frame bowl(Pins fallenPins);
    String symbol();
    int getFrameNo();
    boolean isEndGame();
}
