package qna;

import qna.domain.ContentType;

public class CannotDeleteException extends Exception {
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "contentType=%s, contentId=%d, userId=%s";

    public CannotDeleteException(ContentType contentType, long contentId, String accessUserId) {
        super(String.format(MESSAGE, contentType, contentId, accessUserId));
    }
}