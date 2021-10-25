package bowling.presentation.input;

import java.util.Scanner;

public class PlayerCountInputView {

    private static final String QUESTION = "How many people? ";

    private PlayerCountInputView() {
    }

    public static PlayerCountInputView create() {
        return new PlayerCountInputView();
    }

    public int input() {
        Scanner sc = new Scanner(System.in);
        System.out.print(QUESTION);
        return sc.nextInt();
    }
}
