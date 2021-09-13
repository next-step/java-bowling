package qna.exception;

import qna.CannotDeleteException;

public class WrongUserDeleteTryException extends Exception {

    public WrongUserDeleteTryException(String message) {
        super(message);
    }
}
