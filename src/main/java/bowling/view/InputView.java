package bowling.view;

import java.util.Scanner;

import bowling.domain.Player;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int numberOfPlayers() {
        System.out.print("How many people? ");
        return SCANNER.nextInt();
    }

    public static String playerName(int number) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)? : ", number);
        return SCANNER.next();
    }

    public static int fallenPinsOf(Player player) {
        System.out.printf("%s's turn : ", player.name());
        return SCANNER.nextInt();
    }

}
