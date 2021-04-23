package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String playerName() {
        System.out.print(INPUT_PLAYER_NAME);
        String name = scanner.nextLine().trim();
        return name;
    }

    public String PinCount(int index) {
        System.out.println();
        System.out.printf("%d프레임 투구 : ", index + 1);
        String knockedDownPinCount = scanner.nextLine().trim();
        return knockedDownPinCount;
    }
}
