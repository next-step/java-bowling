package bowling.view;

import bowling.domain.game.FrameNumber;

import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    public static String doInputPlayer() {
        System.out.println("플레이어 이름은(3 english letters)?");
        return scanner.nextLine();
    }

    public static int doInputCountOfPins(FrameNumber frameNumber) {
        System.out.println(frameNumber.toString() + "프레임 투구 : ");
        return Integer.parseInt(scanner.next());
    }
}
