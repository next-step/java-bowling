package bowling.view;

import bowling.domain.Player;
import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public int requestPlayerCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String requestPlayer(int position) {
        System.out.print(String.format("플레이어 %s의 이름은?(3 english letters): ", position + 1));
        return scanner.nextLine().trim();
    }

    public int requestDownPin(Player player) {
        System.out.print(String.format("%s's turn : ", player.getName()));
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
