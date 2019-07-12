package com.jaeyeonling.bowling.domain.frame;

public class FinishedBowlingGameException extends IllegalStateException {

    private static final String ERROR_MESSAGE = "게임이 이미 종료됐습니다.";

    public FinishedBowlingGameException() {
        super(ERROR_MESSAGE);
    }
}
