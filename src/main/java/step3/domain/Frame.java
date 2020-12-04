package step3.domain;

import step3.type.ResultPitchesType;

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
