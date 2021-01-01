package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INSERT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String NTH_FRAME_TRY_IS = "%d프레임 투구 : ";

    private InputView() {}

    public static String askPlayerName() {
        System.out.print(INSERT_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int askNumberOfPins(int frameNo) {
        System.out.printf(NTH_FRAME_TRY_IS, frameNo);
        return Integer.parseInt(SCANNER.nextLine());
    }
}