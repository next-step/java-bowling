package bowling.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int getPlayerCount() {
        System.out.print("How many people? ");
        return scanner.nextInt();
    }

    public String getPlayerName(int playerNumber) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", playerNumber);
        return scanner.next();
    }

    public int getFalledPins(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return scanner.nextInt();
    }
}
