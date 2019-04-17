package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.ResultStatus;

public interface Frame {
    int MAX_SHOTS_COUNT = 2;

    Frame add(int knockDownPinsCount);
    boolean isDone();
    ResultStatus getResultStatus();
}
