package bowling.exception;

import lombok.Getter;

public enum ExceptionType {
    INVALID_NAME_LENGTH("Name length must be 3"),
    INVALID_CLEAR_PIN_COUNT("Clear pin max count is 10");

    @Getter
    private String errorMessage;

    ExceptionType(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
