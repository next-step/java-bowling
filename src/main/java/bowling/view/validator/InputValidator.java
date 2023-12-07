package bowling.view.validator;

import bowling.util.validator.StringValidator;

public class InputValidator {
    private static InputValidator inputValidator;

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public void validatePlayerName(String playerName, String target) {
        StringValidator.validateBlank(playerName, target);
        validateKorean(playerName, target);
    }

    private void validateKorean(String playerName, String target) {
    }

    public void validateBall(String ball, String target) {
    }
}
