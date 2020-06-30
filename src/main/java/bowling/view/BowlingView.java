package bowling.view;

import java.util.Scanner;

public class BowlingView {

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
