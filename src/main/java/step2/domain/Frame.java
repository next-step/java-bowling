package step2.domain;

import step2.type.ResultPitchesType;

public interface Frame {

    int pitches(int pitchesCount);

    int getFrameNo();

    int getScore();

    int getScore(ResultPitchesType type);

    int getCurrentScore();

    Frame next();

    boolean hasNext();

    String getResultString();

    boolean isFinished();
}
