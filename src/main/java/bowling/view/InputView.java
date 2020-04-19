package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.point.Point;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static Player inputPlayers() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return new Player(scanner.nextLine());
    }

    public static Point inputThrowCount(int frameNo) {
        System.out.println(frameNo + "프레임 투구 : ");
        try {
            return Point.of(scanner.nextInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputThrowCount(frameNo);
        }
    }
}
