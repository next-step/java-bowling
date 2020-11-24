package step2.domain;

import step2.strategy.PitchesStrategy;

public interface Frame {

    void pitches(PitchesStrategy strategy);

    int getScore();

    Frame next();

    void linkNext(Frame frame);

    Frame makeNext();
}
