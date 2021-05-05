package bowling.error;

public class CannotCalculateException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "계산할 수 없습니다.";

  public CannotCalculateException() {
    this(DEFAULT_MESSAGE);
  }

  public CannotCalculateException(String message) {
    super(message);
  }
}
