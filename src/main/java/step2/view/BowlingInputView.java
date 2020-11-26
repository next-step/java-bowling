package step2.view;

import java.util.Scanner;

public class BowlingInputView implements InputView{
    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public String getPlayerName() {
        System.out.println("플레이어 이름은?(3 english letters)?:");
        return scanner.nextLine();
    }
}
