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

    public String enterScore(final int index) {
        System.out.printf("%d프레임 투구 : ", index);
        return scanner.nextLine();
    }
}
