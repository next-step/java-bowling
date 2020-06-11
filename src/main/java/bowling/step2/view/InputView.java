package bowling.step2.view;

import bowling.step2.domain.Frame;
import bowling.step2.domain.PlayerName;
import bowling.step2.domain.Score;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputView INSTANCE = new InputView();
    private static final String NEW_LINE = System.lineSeparator();

    private InputView () {}

    public PlayerName inputName () {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return PlayerName.valueOf(SCANNER.nextLine());
    }
    
    public Score inputScore (Frame frame) {
        System.out.printf("%d프레임 투구 : ", frame.getValue());
        return Score.stringOf(SCANNER.nextLine());
    }

    public static InputView getInstance () {
        return INSTANCE;
    }
}