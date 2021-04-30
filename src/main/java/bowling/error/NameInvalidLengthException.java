package bowling.error;

public class NameInvalidLengthException extends RuntimeException{
  private static final String DEFAULT_MESSAGE = "";

  public NameInvalidLengthException(){
    this(DEFAULT_MESSAGE);
  }

  public NameInvalidLengthException(String message) {
    super(message);
  }
}
