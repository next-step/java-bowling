package bowling.view;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public String userName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.next();
    }
}
