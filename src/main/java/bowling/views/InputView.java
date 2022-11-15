package bowling.views;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int inputNumberOfPlayers() {
        System.out.print("How many people? ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
}

    public static int inputNumberOfPinsHit(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return Integer.parseInt(SCANNER.nextLine());
    }

}
