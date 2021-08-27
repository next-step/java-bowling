package bowling.step2.inputView;

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

    public static int getPitch(int i) {
        printGetPitch(i);
        return Integer.parseInt(SC.nextLine());
    }

    private static void printGetPitch(int i) {
        System.out.print(i + "프레임 투구 : ");
    }
}
