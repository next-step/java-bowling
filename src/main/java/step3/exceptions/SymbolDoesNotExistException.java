package step3.exceptions;

public class SymbolDoesNotExistException extends IllegalArgumentException {
    private static final String SYMBOL_EXCEPTION = "현재 상태에는 symbol이 존재하지 않습니다.";

    public SymbolDoesNotExistException() {
        super(SYMBOL_EXCEPTION);
    }
}
