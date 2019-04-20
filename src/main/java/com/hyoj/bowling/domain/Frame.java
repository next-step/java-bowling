package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.ResultStatus;

public interface Frame {
    int MAX_THROW_COUNT = 2;

    Frame throwBowlingBall(Pins pins);
    boolean isDone();
    ResultStatus getResultStatus();
}
