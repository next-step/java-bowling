package bowling.view;

import java.util.Scanner;

public class DosInputView implements InputView {
    private final Scanner scanner;

    public DosInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int inputPlayerSize() {
        return inputNumber(Text.INPUT_PLAYER_SIZE);
    }

    @Override
    public String inputName() {
        return inputLine(Text.INPUT_NAME);
    }

    @Override
    public int inputTurnScore(String playerName) {
        return inputNumber(
                Text.INPUT_TURN_SCORE.format(playerName)
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

    private int inputNumber(Text guideText) {
        return inputNumber(guideText.toString());
    }

    private String inputLine(String guideText) {
        System.out.print(guideText);

        return scanner.nextLine();
    }

    private String inputLine(Text guideText) {
        return inputLine(guideText.toString());
    }

    private enum Text {
        INPUT_PLAYER_SIZE("How many people? "),
        INPUT_NAME("플레이어 이름은(3 english letters)?: "),
        INPUT_TURN_SCORE("%s's turn : "),
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
