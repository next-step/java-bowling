package bowling.frame.domain;

import bowling.pin.domain.Pin;

public interface Frame {

    static Frame ofNormal() {
        return new NormalFrame();
    }

    static Frame ofFinal() {
        return new FinalFrame();
    }

    void roll(Pin felled);

    boolean isEnd();

    FrameScore getScore();


}
