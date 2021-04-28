package bowling.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int peopleNumber() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String name(int peopleNumber) {
        System.out.print("플레이어 " + peopleNumber + "의 이름은?(3 english letters): ");
        return scanner.nextLine();
    }

    public int pinNumber(String name) {
        System.out.print(name + "'s turn : ");
        return Integer.parseInt(scanner.nextLine());
    }
}