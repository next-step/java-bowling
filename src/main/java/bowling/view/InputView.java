package bowling.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {

    }

    public static String inputPlayerName() {
        return inputWithText("플레이어 이름은?: ");
    }

    public static int inputPitchNumber(int frameNumber) {
        try {
            return Integer.parseInt(inputWithText(frameNumber + "프레임 투구 : "));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputPitchNumber(frameNumber);
        }
    }

    private static String inputWithText(String text) {
        System.out.print(text);
        return input();
    }

    private static String input() {
        try {
            return BUFFERED_READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
