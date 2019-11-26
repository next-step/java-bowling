package game.bowling.view;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by yusik on 2019/11/20.
 */
public class InputView {

    private final Scanner scanner;

    public InputView(InputStream in) {
        scanner = new Scanner(in);
    }

    public String receivePlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String playerName = scanner.nextLine();
        return playerName.trim();
    }

    public int receiveScore(int frameNo) {
        System.out.print(String.format("%d 프레임 투구 : ", frameNo));
        String score = scanner.nextLine();
        return Integer.parseUnsignedInt(score.trim());
    }
}
