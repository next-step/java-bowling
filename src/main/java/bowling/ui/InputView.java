package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_DESCRIPTION = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.printf(INPUT_PLAYER_NAME_DESCRIPTION);
        return scanner.nextLine();
    }

    public static int inputKnockedDownPinCount(int frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber);
        return scanner.nextInt();
    }
}
