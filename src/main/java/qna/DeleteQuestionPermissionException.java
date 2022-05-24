package qna;

public class DeleteQuestionPermissionException extends RuntimeException {

    public DeleteQuestionPermissionException() {
        super("질문을 삭제할 권한이 없습니다.");
    }
}
