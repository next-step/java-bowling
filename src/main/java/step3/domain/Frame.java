package step3.domain;

import step3.type.ResultPitchesType;

public interface Frame {
    String NORMAL = "normal";
    String FINAL = "final";

    int pitches(int pitchesCount);

    int getFrameNo();

    int getScore();

    int getScore(ResultPitchesType prevType);

    ResultPitchesType getType();

    int getCurrentScore();

    int getFirstScore();

    String getFrameType();

    Frame next();

    boolean hasNext();

    String getResultString();

    boolean isFinished();
}
