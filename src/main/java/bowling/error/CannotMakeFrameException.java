package bowling.error;

public class CannotMakeFrameException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "더 이상 프레임을 채울 수 없습니다.";

  public CannotMakeFrameException() {
    this(DEFAULT_MESSAGE);
  }

  public CannotMakeFrameException(String message) {
    super(message);
  }
}
