package step2.view;

import java.util.Scanner;

public class InputView {
    private static final String MESSAGE_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner SC = new Scanner(System.in);

    public static String requirePlayerName() {
        System.out.println(MESSAGE_PLAYER_NAME);
        return SC.nextLine();
    }
    public static int requireThrowBallNum(int frameNum) {
        System.out.print(frameNum + "프레임 투구 : ");
        return Integer.parseInt(SC.nextLine());
    }
}
