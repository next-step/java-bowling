package step2.domain;

import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

public interface Frame {

    int pitches(PitchesStrategy strategy);

    int getFrameNo();

    int getScore();

    int getScore(ResultPitchesType type);

    int getCurrentScore();

    Frame next();

    boolean hasNext();

    String getResultString();

    boolean isFinished();
}
