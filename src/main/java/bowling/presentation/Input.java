package bowling.presentation;

import bowling.domain.Player;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static Player askName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return new Player(scanner.nextLine().trim());
    }
}
