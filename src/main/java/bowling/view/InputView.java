package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.text.MessageFormat;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static int getPlayerCount() {
        System.out.print("player count?: ");
        return nextInt();
    }

    public static String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int getNumberOfPins(Player player) {
        System.out.println();
        System.out.print(MessageFormat.format("{0}'s turn : ", player.getName()));
        return nextInt();
    }

    private static int nextInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

}
