package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.frame.Frame;
import bowling.domain.point.Point;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static Player inputPlayers() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return new Player(scanner.nextLine());
    }

    public static Point inputThrowCount(int frameNo, Frame frame) {
        int leftPoint = frame.getPoints().getLeftPoint();
        try {
            System.out.println(frameNo + "프레임 투구 : ");
            Point point = Point.of(scanner.nextInt());
            return point.checkLeftPin(leftPoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputThrowCount(frameNo, frame);
        }
    }
}
