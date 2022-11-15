package bowling.step3.view;

import java.util.Scanner;

public class InputView {

    private static final String NAME_INPUT_TEXT = "플레이어 이름은(3 english letters)?: ";
    private static final String FRAME_SCORE_INPUT_TEXT = "프레임 투구 : ";
    private static final Scanner sc = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print(NAME_INPUT_TEXT);
        return sc.next();
    }

    public static int inputFallenPinCounts(int index) {
        System.out.print(System.lineSeparator()+ index + FRAME_SCORE_INPUT_TEXT);
        return sc.nextInt();
    }
}
