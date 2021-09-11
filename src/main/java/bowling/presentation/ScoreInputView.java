package bowling.presentation;

import java.util.Scanner;

public class ScoreInputView {

    private static final String QUESTION = "%d프레임 투구 : ";

    private ScoreInputView() {
    }

    public static ScoreInputView create() {
        return new ScoreInputView();
    }

    public int input(int round) {
        Scanner sc = new Scanner(System.in);
        System.out.printf(QUESTION, round);
        return sc.nextInt();
    }
}
