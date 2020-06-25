package bowling.ui;

import bowling.domain.Player;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Player inputPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        String name = scanner.nextLine();
        return Player.create(name);
    }
}
