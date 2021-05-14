package bowling.exception;

public final class PlayerListNullPointerException extends RuntimeException {

    private final String MESSAGE = "List<Player> 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

