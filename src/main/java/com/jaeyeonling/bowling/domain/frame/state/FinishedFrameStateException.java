package com.jaeyeonling.bowling.domain.frame.state;

class FinishedFrameStateException extends IllegalStateException {

    private static final String ERROR_MESSAGE = "이미 끝난 프레임 상태입니다.";

    FinishedFrameStateException() {
        super(ERROR_MESSAGE);
    }
}
