package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView{
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String playerName() {
        return scanner.nextLine();
    }

    @Override
    public int pinNumber() {
        int pinNumber = scanner.nextInt();
        scanner.nextLine();

        return pinNumber;
    }
}
