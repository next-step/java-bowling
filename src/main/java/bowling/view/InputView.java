package bowling.view;

import bowling.domain.Player;

import java.text.MessageFormat;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static int getNumberOfPlayer() {
        System.out.print("How many people? ");
        return nextInt();
    }

    public static String getPlayerName(int numberOfPlayer) {
        System.out.print(MessageFormat.format("플레이어 {0}의 이름은(3 english letters)?: ", numberOfPlayer));
        return scanner.nextLine();
    }

    public static int getNumberOfPins(Player player) {
        System.out.println();
        System.out.print(MessageFormat.format("{0}''s turn : ", player.getName()));
        return nextInt();
    }

    private static int nextInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

}
