package bowling_step3.view;

import bowling_step3.domain.Player;

import java.util.Scanner;

public class InputUi {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUi() {

    }

    public static int inputCountOfPlayer() {
        System.out.print("How many people? ");
        return SCANNER.nextInt();
    }

    public static String inputPlayer(int number) {
        System.out.print("플레이어" + number + "의 이름은(3 english letters)? : ");
        return SCANNER.next();
    }

    public static int inputFrame(Player player) {
        System.out.print(String.format("%s's turn : ", player.getName()));
        return SCANNER.nextInt();
    }

    public static void close() {
        SCANNER.close();
    }
}
