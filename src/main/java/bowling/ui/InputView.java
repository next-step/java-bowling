package bowling.ui;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);

    public static int inputtPlayerCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static Player inputPlayer(int position) {
        System.out.print(String.format("플레이어 %s의 이름은?(3 english letters): ", position + 1));
        String player = scanner.nextLine();
        return Player.create(player, position);
    }

    public static int inputFramePoint(Player player) {
        System.out.print(String.format("%s's turn : ", player.getName()));
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
