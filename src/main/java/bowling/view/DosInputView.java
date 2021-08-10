package bowling.view;

import java.util.Scanner;

public class DosInputView implements InputView {
    private final Scanner scanner;

    public DosInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputName() {
        return inputLine(Text.INPUT_NAME);
    }

    @Override
    public int inputTurnScore(int currentFrameNumber) {
        return inputNumber(
                Text.INPUT_TURN_SCORE.format(currentFrameNumber)
        );
    }

    private int inputNumber(String guideText) {
        String input = inputLine(guideText);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(Text.NOT_NUMBER_FORMAT);
            return inputNumber(guideText);
        }
    }

    private String inputLine(String guideText) {
        System.out.print(guideText);

        return scanner.nextLine();
    }

    private String inputLine(Text guideText) {
        return inputLine(guideText.toString());
    }

    private enum Text {
        INPUT_NAME("플레이어 이름은(3 english letters)?: "),
        INPUT_TURN_SCORE("%d프레임 투구 : "),
        NOT_NUMBER_FORMAT("숫자의 형식이 아닙니다.");

        private final String str;

        Text(String str) {
            this.str = str;
        }

        public String format(Object... objs) {
            return String.format(str, objs);
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
