package com.hyoj.bowling.domain;

public interface Frame {
    Frame add(int knockDownPinsCount);

    boolean isDone();

    MarkType getFinalMarkType();
}
