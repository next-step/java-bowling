package bowling.exception;

public final class NameIncludeVariableLanguageException extends RuntimeException {

    private final String MESSAGE = "이름에 영어 이외에 다른 언어들이 섞여있습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
