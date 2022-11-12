package bowling;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return sc.nextLine();
    }

    public static int inputKnockDownCount(int frameOrderToThrow) {
        System.out.printf("\n%d프레임 투구 : ", frameOrderToThrow);
        return Integer.parseInt(sc.nextLine());
    }



}
