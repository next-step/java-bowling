package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputPlayerCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputPlayerName(int playerNumber) {
        System.out.printf("플레이어 %d이름은(3 english letters)?: ", playerNumber);
        return scanner.nextLine();
    }

    public static int inputBowl(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return Integer.parseInt(scanner.nextLine());
    }

}
