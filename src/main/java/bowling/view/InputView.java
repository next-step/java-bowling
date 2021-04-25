package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";

    private final Scanner scanner = new Scanner(System.in);

    public String playerName() {
        System.out.print(INPUT_PLAYER_NAME);
        String name = scanner.nextLine().trim();
        return name;
    }

    public String pinCount(int index) {
        System.out.println();
        System.out.printf("%d프레임 투구 : ", index + 1);
        String knockedDownPinCount = scanner.nextLine().trim();
        return knockedDownPinCount;
    }

    public void close() {
        scanner.close();
    }
}
