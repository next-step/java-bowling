package bowling.step4.view;

import bowling.step4.domain.Player;
import bowling.step4.domain.Score;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputView INSTANCE = new InputView();

    private InputView() { }

    public int inputPlayerCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public Player inputName(int index) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", index);
        return Player.valueOf(SCANNER.nextLine());
    }

    public Score inputScore(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        return Score.stringOf(SCANNER.nextLine());
    }

    public static InputView getInstance() {
        return INSTANCE;
    }
}