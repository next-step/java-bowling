package bowling.presentation.input;

import java.util.Scanner;

public class ScoreInputView {

    private static final String QUESTION = "%d프레임 투구 : ";

    private ScoreInputView() {
    }

    public static ScoreInputView create() {
        return new ScoreInputView();
    }

    public int inputStart() {
        return input(1);
    }

    public int inputLast() {
        return input(10);
    }

    public int input(int frame) {
        Scanner sc = new Scanner(System.in);
        System.out.printf(QUESTION, frame);
        return sc.nextInt();
    }
}
