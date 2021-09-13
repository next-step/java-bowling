package step3.view;

import java.util.Scanner;

public class InputView {
    private static final String THROW_BOWL_NUM = "%s 프레임 투구 : ";
    private static final String USER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner SC = new Scanner(System.in);

    public static int requireThrowBallNum(int frameNum) {
        System.out.printf(THROW_BOWL_NUM, frameNum);
        return Integer.parseInt(SC.nextLine());
    }

    public static String requireUserName() {
        System.out.println(USER_NAME);
        return SC.nextLine();
    }
}
