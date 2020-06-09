package bowling.step2.view;

import bowling.step2.domain.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputView INSTANCE = new InputView();

    private InputView () {}

    public Player inputName () {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return Player.valueOf(SCANNER.nextLine());
    }

    public static InputView getInstance () {
        return INSTANCE;
    }
}