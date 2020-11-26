package bowling.domain.member;

public class InvalidMemberNameLengthException extends RuntimeException {
    private static final String MESSAGE = "참여자 이름은 3글자여야 합니다.";

    public InvalidMemberNameLengthException() {
        super(MESSAGE);
    }
}
