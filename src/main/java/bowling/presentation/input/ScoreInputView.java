package bowling.presentation.input;

import java.util.Scanner;

public class ScoreInputView {

    private static final String QUESTION = "%s's turn : ";

    private ScoreInputView() {
    }

    public static ScoreInputView create() {
        return new ScoreInputView();
    }

    public int input(String name) {
        Scanner sc = new Scanner(System.in);
        System.out.printf(QUESTION, name);
        return sc.nextInt();
    }

}
