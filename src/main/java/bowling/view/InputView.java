package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return SCANNER.nextLine();
    }
}
