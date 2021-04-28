package qna.error;

public class NotEnoughPermissionException extends CannotDeleteException {
  private static final long serialVersionUID = 1L;
  private static final String defaultExceptionMessage = "질문을 삭제할 권한이 없습니다.";

  public NotEnoughPermissionException() {
    this(defaultExceptionMessage);
  }

  public NotEnoughPermissionException(String message) {
    super(message);
  }
}
