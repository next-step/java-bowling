package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.score.Score;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Player askPlayer() {
        System.out.println("플레이어 이름은(3 english letters)? : ");
        return Player.of(SCANNER.nextLine());
    }

    public static Score askScore(int frameIndex) {
        System.out.println(frameIndex + "프레임 투구 : ");
        return Score.of(SCANNER.nextInt());
    }
}
