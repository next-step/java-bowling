package bowling.view;

import bowling.domain.PlayerName;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_QUESTION = "플레이어 이름은(3 english letters)?: ";

    public static void closeScan() {
        scanner.close();
    }

    public static PlayerName scanName() {
        System.out.print(NAME_QUESTION);
        return new PlayerName(scanner.nextLine());
    }
}
