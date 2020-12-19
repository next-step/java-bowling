package bowling.view;

import bowling.domain.Pitch;
import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String INPUT_PLAYER = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_SCORE = "프레임 투구 : ";

    public Player inputPlayer() {
        System.out.print(INPUT_PLAYER);
        String name = scanner.nextLine();
        return Player.from(name);
    }

    public Pitch inputScore() {
        System.out.println();
        System.out.print(INPUT_SCORE);
        String score = scanner.nextLine();
        return Pitch.from(Integer.parseInt(score));
    }
}
