package qna.exception;

public class NotOwnedContentException extends RuntimeException{

    private static final String MESSAGE = "해당 게시물의 소유자만 삭제할 수 있습니다.";

    public NotOwnedContentException() {
        super(MESSAGE);
    }

}
