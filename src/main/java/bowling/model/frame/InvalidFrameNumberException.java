package bowling.model.frame;

import static bowling.model.frame.FrameNumber.MAX;
import static bowling.model.frame.FrameNumber.MIN;

class InvalidFrameNumberException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "프레임 번호는 %s~%s의 값만 허용합니다";

    InvalidFrameNumberException() {
        super(String.format(ERROR_MESSAGE, MIN, MAX));
    }
}