package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int scanCountOfPlayers() {
        System.out.print("How many people?");
        return scanInt();
    }

    public static String scanName(int index) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", index);
        return SCANNER.nextLine();
    }

    public static int scanScore(Player player) {
        System.out.printf("%s's turn : ", player.getName().getName());
        return scanInt();
    }

    private static int scanInt() {
        return Integer.parseInt(SCANNER.nextLine());
    }
}
