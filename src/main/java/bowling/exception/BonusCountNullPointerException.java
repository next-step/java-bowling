package bowling.exception;

public final class BonusCountNullPointerException extends RuntimeException {

    private final String MESSAGE = "BonusCount 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
