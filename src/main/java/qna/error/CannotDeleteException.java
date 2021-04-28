package qna.error;

public class CannotDeleteException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private static final String defaultExceptionMessage = "삭제가 불가능합니다.";

  public CannotDeleteException() {
    this(defaultExceptionMessage);
  }

  public CannotDeleteException(String message) {
    super(message);
  }
}