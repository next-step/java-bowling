package bowling.domain.participant;

import bowling.domain.exception.ServiceException;

public class IllegalNameException extends ServiceException {

    private static final String MESSAGE = "이름은 알파벳 세글자여야 합니다.";

    public IllegalNameException() {
        super(MESSAGE);
    }
}
