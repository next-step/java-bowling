package bowling;

public class IllegalPlayerNameException extends IllegalArgumentException{
    public IllegalPlayerNameException(String errorMessage) {
        super(errorMessage);
    }
}
