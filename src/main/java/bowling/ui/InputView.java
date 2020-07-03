package bowling.ui;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);

    public static Player inputPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        String player = scanner.nextLine();
        return Player.create(player);
    }

    public static int inputFramePoint(int frame) {
        System.out.println(String.format("%d프레임 투구 :", frame + 1));
        return Integer.parseInt(scanner.nextLine());
    }
}
