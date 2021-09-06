package bowling.step2.inputview;

import java.util.Scanner;

public class InputView {
    private static final Scanner SC = new Scanner(System.in);

    public static String getName() {
        printGetName();
        return SC.nextLine();
    }

    private static void printGetName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
    }

    public static int getPitch(int frameCount) {
        printGetPitch(frameCount);
        return Integer.parseInt(SC.nextLine());
    }

    private static void printGetPitch(int frameCount) {
        System.out.print(frameCount + "프레임 투구 : ");
    }
}
