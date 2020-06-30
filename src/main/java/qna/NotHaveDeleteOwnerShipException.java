package qna;

public class NotHaveDeleteOwnerShipException extends CannotDeleteException {

    public static final String NO_OWNERSHIP = "질문을 삭제할 권한이 없습니다.";

    public NotHaveDeleteOwnerShipException() {
        super(NO_OWNERSHIP);
    }
}
