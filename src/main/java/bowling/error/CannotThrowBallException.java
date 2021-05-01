package bowling.error;

public class CannotThrowBallException extends RuntimeException{
  private static final String DEFAULT_MESSAGE = "더 이상 공을 던질 수 없습니다.";

  public CannotThrowBallException(){
    this(DEFAULT_MESSAGE);
  }

  public CannotThrowBallException(String message) {
    super(message);
  }
}
