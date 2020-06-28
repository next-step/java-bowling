package bowling.ui;

import bowling.domain.frame.FrameNumber;
import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() { }

    public static Player inputPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        String name = scanner.nextLine();
        return Player.create(name);
    }

    public static FrameNumber inputScore(FrameNumber frameNumber) {
        System.out.println(String.format("%d프레임 투구 : ", frameNumber.toString()));
        int number = scanner.nextInt();
        return FrameNumber.create(number);
    }
}
