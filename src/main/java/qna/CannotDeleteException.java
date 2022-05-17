package qna;

import qna.domain.User;

public class CannotDeleteException extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message, User user) {
        super(String.format("%s : %s", message, user));
    }
}