package bowling.view;

import java.util.Scanner;

public class BowlingConsoleInputView {

    private final Scanner scanner;

    public BowlingConsoleInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String enterPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }
}
