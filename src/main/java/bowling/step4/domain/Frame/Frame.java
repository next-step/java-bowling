package bowling.step4.domain.Frame;

import bowling.step4.domain.Pitches;

public interface Frame {

    Boolean isEndedFrame();

    Boolean isFinalFrame();

    void add(int count);

    int firstPitch();

    int secondPitch();

    Pitches pitches();
}
