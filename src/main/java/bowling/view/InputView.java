package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_N_FRAME_THROW = "%d프레임 투구: ";

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
        return inputStringValue();
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
