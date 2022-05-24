package qna;

import qna.domain.ContentType;

public class NotFoundDeleteHistoryException extends RuntimeException {

    private static final String MESSAGE = "contentType=%s, activeContentId=%d";

    public NotFoundDeleteHistoryException(ContentType contentType, long contentId) {
        super(String.format(MESSAGE, contentType, contentId));
    }
}
