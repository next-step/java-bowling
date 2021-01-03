package bowling.bowlingexception;

public class InvalidNameFormatException extends IllegalArgumentException {

    public InvalidNameFormatException() {
        super("영문자 3개만 허용됩니다.");
    }
}
