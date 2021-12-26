package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner sc = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static String printInputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return sc.nextLine();
    }

    public static int printInputHittingPins() {
        System.out.print("프레임 투구 : ");
        return Integer.parseInt(sc.nextLine());
    }

}
