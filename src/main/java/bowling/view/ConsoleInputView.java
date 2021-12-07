package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView{
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void questionPlayerName() {
        System.out.print("What is player's name? (3 English Letters) ");
    }

    @Override
    public String playerName() {
        return scanner.nextLine();
    }

    @Override
    public void questionPinNumber(int frame) {
        System.out.printf("Pin number of %d frame: ", frame);
    }

    @Override
    public int pinNumber() {
        int pinNumber = scanner.nextInt();
        scanner.nextLine();

        return pinNumber;
    }
}
