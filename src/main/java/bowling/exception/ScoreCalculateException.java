package bowling.exception;

public class ScoreCalculateException extends RuntimeException {

  public ScoreCalculateException() {
    super();
  }

  public ScoreCalculateException(final String message) {
    super(message);
  }
}
