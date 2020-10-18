package bowling.domain.view;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pin;

import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?";
    private static final String CURRENT_FRAME = "프레임 투구 : ";
    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.println(ASK_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static Pin inputPinRoll(int frameNumber, Frame frame) {
//        System.out.println(frameNumber + CURRENT_FRAME);
//        return scanner.nextInt();
        int leftPoint = frame.getPins().getLeftPin();
        System.out.println(frameNumber + CURRENT_FRAME);
        return new Pin(scanner.nextInt());
    }


}
