package bowling.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return scanner.next();
    }
}
