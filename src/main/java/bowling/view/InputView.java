package bowling.view;

import bowling.vo.GameUser;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static GameUser askUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return new GameUser(SCANNER.nextLine());
    }
}
