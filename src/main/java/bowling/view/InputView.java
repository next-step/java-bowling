package bowling.view;

import bowling.model.Player;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputPlayerCount() {
        System.out.println("몇 명인가요?");
        int count = scanner.nextInt();
        scanner.nextLine();
        return count;
    }

    public String inputPlayerName(int index) {
        System.out.printf("플레이어%d 이름은(3 english letters)?", index);
        return scanner.nextLine();
    }

    public int inputFramePinCount(Player player) {
        System.out.println();
        System.out.println(player.getName() + "'s turn");
        int pinCount = scanner.nextInt();
        scanner.nextLine();
        return pinCount;
    }
}
