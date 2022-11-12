package bowling;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int inputKnockDownCount(int frameOrderToThrow) {
        System.out.printf("\n%d프레임 투구 : ", frameOrderToThrow);
        return Integer.parseInt(scanner.nextLine());
    }



}
