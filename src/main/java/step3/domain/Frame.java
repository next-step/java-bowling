package step3.domain;

import step3.type.ResultPitchesType;

public interface Frame {

    Frame pitches(int pitchesCount);

    int getFrameNo();

    int getScore();

    int getScore(ResultPitchesType prevType);

    ResultPitchesType getType();

    int getCurrentScore();

    int getFirstScore();

    Frame next();

    boolean hasNext();

    String getResultString();

    boolean isFinished();
}
