package bowling;

public class IllegalPinFallException extends IllegalArgumentException {
    public IllegalPinFallException(String errorMessage) {
        super(errorMessage);
    }
}
