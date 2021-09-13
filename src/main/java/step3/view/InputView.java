package step3.view;

import java.util.Scanner;

public class InputView {
    private static final String THROW_BOWL_NUM = "%s 프레임 투구 : ";
    private static final Scanner SC = new Scanner(System.in);

    public static int requireThrowBallNum(int frameNum) {
        System.out.printf(THROW_BOWL_NUM, frameNum);
        System.out.println();
        return Integer.parseInt(SC.nextLine());
    }
}
