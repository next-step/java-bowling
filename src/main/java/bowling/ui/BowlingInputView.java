package bowling.ui;

import java.util.Scanner;

public class BowlingInputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PLAYER_NAME_INPUT_PHRASE = "플레이어 이름은(3 english letters)?: ";
    private static final String SCORE_INPUT_PHRASE = "프레임 투구 : ";

    private BowlingInputView() {

    }

    public static void printNameInputPhrase() {
        System.out.print(PLAYER_NAME_INPUT_PHRASE);
    }

    public static void printScoreInputPhrase(String frameNo) {
        System.out.print(frameNo + SCORE_INPUT_PHRASE);
    }

    public static String readOneLineString() {
        return SCANNER.nextLine();
    }

    public static int readInteger() {
        int input = SCANNER.nextInt();
        SCANNER.nextLine();

        return input;
    }
}
