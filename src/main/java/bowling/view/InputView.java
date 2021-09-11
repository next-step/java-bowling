package bowling.view;

import bowling.domain.User;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String extractUsername(int index) {
        System.out.print("플레이어" + index + "의 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int extractFrameResult(User user) {
        System.out.println(user + "'s turn : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int extractPlayerCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
