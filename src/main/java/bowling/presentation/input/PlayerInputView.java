package bowling.presentation.input;

import java.util.Scanner;

public class PlayerInputView {


    private static final String PLAYER_COUNT_QUESTION = "How many people? ";

    private static final String QUESTION = "플레이어 %d의 이름은(3 english letters)?: ";

    private final int index;

    private PlayerInputView(int index) {
        this.index = index;
    }

    public static PlayerInputView create(int index) {
        return new PlayerInputView(index);
    }

    public String input() {
        Scanner sc = new Scanner(System.in);
        System.out.printf(QUESTION, index);
        return sc.nextLine();
    }
}
