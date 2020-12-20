package bowling.view;

import bowling.domain.Player;
import bowling.domain.point.Point;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
    public static final String FRAME_PITCH = "프레임 투구 : ";

    public static Player inputPlayerName() {
        System.out.println(PLAYER_NAME);
        return Player.of(scanner.nextLine());
    }

    public static Point inputPitchBowl(int stage) {
        System.out.print((stage + 1) + FRAME_PITCH);
        return Point.valueOfString(scanner.nextLine());
    }


}
