package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.Score;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_QUESTION = "플레이어 이름은(3 english letters)?: ";
    private static final String SCORE_QUESTION_FORMAT = "%s프레임 투구 : ";

    public static void closeScan() {
        scanner.close();
    }

    public static PlayerName scanName() {
        System.out.print(NAME_QUESTION);
        return new PlayerName(scanner.nextLine());
    }

    public static Score scanScore(int frameNumber) {
        System.out.printf(SCORE_QUESTION_FORMAT, frameNumber);
        return Score.of(Integer.parseInt(scanner.nextLine()));
    }
}
