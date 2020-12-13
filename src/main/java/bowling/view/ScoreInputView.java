package bowling.view;

import java.util.Scanner;

public class ScoreInputView extends BaseView {

    private static final String SCORE_INPUT_MESSAGE = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String display(int frameNumber) {
        append(String.format(SCORE_INPUT_MESSAGE, frameNumber));
        printAndClear(PrintType.NOT_NEW_LINE);
        return scanner.next();
    }
}
