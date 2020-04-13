package bowling.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String ONLY_ENGLISH_TEXT = "^[a-zA-Z]*$";
    private static final int USER_NAME_LENGTH = 3;
    private static InputView inputView = new InputView();

    private Scanner scanner = new Scanner(System.in);

    public String enterUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        String userName = scanner.nextLine();
        validateUserName(userName);
        return userName;
    }

    public void validateUserName(String userName) {
        if (!Pattern.matches(ONLY_ENGLISH_TEXT, userName) || userName.length() != USER_NAME_LENGTH) {
            throw new IllegalArgumentException("영어로만 입력가능하며 길이는 3글자여야 합니다.");
        }
    }

    public static InputView getInputView() {
        return inputView;
    }
}
