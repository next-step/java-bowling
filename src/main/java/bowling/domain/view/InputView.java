package bowling.domain.view;

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

    public static int inputPinRoll(int frame) {
        System.out.println(frame + CURRENT_FRAME);
        return scanner.nextInt();
    }


}
