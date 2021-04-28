package step4.view.;

import bowling.domain.Name;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NUMBER_OF_PLAYER = "How many people? ";

    private final Scanner scanner = new Scanner(System.in);

    public String numberOfPlayer() {
        System.out.print(INPUT_NUMBER_OF_PLAYER);
        String numberOfPlayer = scanner.nextLine().trim();
        return numberOfPlayer;
    }

    public String playerName(int index) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", index + 1);
        String name = scanner.nextLine().trim();
        return name;
    }

    public String pinCount(Name name) {
        System.out.println();
        System.out.printf("%s's turn : ", name.name());
        String knockedDownPinCount = scanner.nextLine().trim();
        return knockedDownPinCount;
    }

    public void close() {
        scanner.close();
    }
}
