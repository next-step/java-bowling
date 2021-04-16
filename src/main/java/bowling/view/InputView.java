package bowling.view;

import java.util.Scanner;

public class InputView {
    private final Scanner in = new Scanner(System.in);

    public String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return in.nextLine();
    }
}
