package bowling.view;

import bowling.domain.Pin;
import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Player getPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return new Player(sc.nextLine());
    }

    public static Pin getNextPin(int currentFrameNumber) {
        System.out.print(currentFrameNumber + "프레임 투구 : ");
        return new Pin(sc.nextInt());
    }

}
