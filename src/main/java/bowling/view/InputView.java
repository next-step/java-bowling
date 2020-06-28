package bowling.view;

import bowling.domain.GameUser;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static GameUser askUserName() {
        System.out.printf("플레이어 이름은(3 english letters)?:");
        return new GameUser(SCANNER.nextLine());
    }
}
