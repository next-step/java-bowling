package step2.domain;

import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

public interface Frame {

    int pitches(PitchesStrategy strategy);

    int getFrameNo();

    int getScore();

    int getFirstScore();

    int getCurrentScore();

    ResultPitchesType getPitchesType();

    Frame next();

    boolean hasNext();

    Frame makeNext();

    String getResultString();

    boolean isFinished();
}
