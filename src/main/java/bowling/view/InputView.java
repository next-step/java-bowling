package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPlayerCount() {
        System.out.print("How many people? ");
        return scanner.nextInt();
    }

    public static String inputPlayerName(int playerNumber) {
        System.out.printf("플레이어 %d의 이름은?(3 english letters): ", playerNumber);
        return scanner.next();
    }

    public static int inputPinNumber(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return scanner.nextInt();
    }
}
