package bowling.error;

public class InvalidFallenPinsException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "쓰러트린 핀의 개수가 정확하지 않습니다.";

  public InvalidFallenPinsException() {
    this(DEFAULT_MESSAGE);
  }

  public InvalidFallenPinsException(String message) {
    super(message);
  }
}
