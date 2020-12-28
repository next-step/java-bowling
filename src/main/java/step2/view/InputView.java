package step2.view;

import step2.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String PITCH = "프레임 투구 : ";

    public static Player getPlayer() {
        System.out.print(PLAYER_NAME);
        return Player.from(scanner.nextLine());
    }

    public static int getScore(int index) {
        System.out.println();
        System.out.print(index + PITCH);
        return Integer.parseInt(scanner.nextLine());
    }
}
