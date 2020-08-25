package bowling.view;

import bowling.domian.player.Player;

import java.util.Scanner;

public class InputView {
    private static final String GET_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String GET_ROLLING_PIN_COUNT_MESSAGE = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static Player getPlayer() {
        System.out.println(GET_PLAYER_NAME_MESSAGE);

        String name = scanner.nextLine();

        return Player.get(name);
    }

    public static int getFalledPinsCount(int frameCount) {
        System.out.println(String.format(GET_ROLLING_PIN_COUNT_MESSAGE, frameCount));

        return scanner.nextInt();
    }
}
