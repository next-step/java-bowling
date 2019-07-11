package com.jaeyeonling.bowling.frame.state;

class AlreadyFinishedFrameStateException extends IllegalStateException {

    private static final String ERROR_MESSAGE = "이미 끝난 프레임 상태입니다.";

    AlreadyFinishedFrameStateException() {
        super(ERROR_MESSAGE);
    }
}
