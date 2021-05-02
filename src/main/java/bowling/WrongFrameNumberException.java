package bowling;

public class WrongFrameNumberException extends IllegalArgumentException {
    public WrongFrameNumberException(String s) {
        super(s);
    }
}
