package bowling.view;

import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_N_FRAME_THROW = "%d프레임 투구: ";
    private static final String PLAYER_NAME_NULL_OR_EMPTY_EXCEPTION_STATEMENT = "플레이어 이름이 널이거나 빈 문자입니다";
    private static final String PLAYER_NAME_LENGTH_EXCEPTION_STATEMENT = "플레이어 이름의 길이가 3자가 아닙니다";
    private static final int PLAYER_NAME_LENGTH = 3;

    private String inputStringValue() {
        return scanner.nextLine();
    }

    private int inputIntValue() {
        int intValue = scanner.nextInt();
        scanner.nextLine();
        return intValue;
    }

    public String inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME);
        String playerName = inputStringValue();
        validate(playerName);
        return playerName;
    }

    private void validate(String playerName) {
        if (Objects.isNull(playerName) || playerName.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_NULL_OR_EMPTY_EXCEPTION_STATEMENT);
        }

        if (playerName.length() == PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_EXCEPTION_STATEMENT);
        }
    }

    public int inputNFrameThrow(int frameNumber) {
        System.out.print(System.lineSeparator());
        System.out.printf(INPUT_N_FRAME_THROW, frameNumber);
        return inputIntValue();
    }


    public void scannerClose() {
        scanner.close();
    }
}
