package bowling.presentation.input;

import java.util.Scanner;

public class PlayerInputView {

    private static final String QUESTION = "플레이어 이름은(3 english letters)?: ";

    private PlayerInputView() {
    }

    public static PlayerInputView create() {
        return new PlayerInputView();
    }

    public String input() {
        Scanner sc = new Scanner(System.in);
        System.out.print(QUESTION);
        return sc.nextLine();
    }
}
