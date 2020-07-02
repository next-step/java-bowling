package bowling.ui;

import bowling.domain.Pin;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_COMMENT = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PIN_COMMENT_FORMAT = "%d프레임 투구 : ";
    private static final Scanner input = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_COMMENT);
        return input.nextLine();
    }

    public static Pin getPin(int round) {
        System.out.println(String.format(INPUT_PIN_COMMENT_FORMAT, round));
        return new Pin(Integer.parseInt(input.nextLine()));
    }
}
