package bowling.view;

import java.util.Scanner;

public class InputView {
    public static String requestPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int requestFallingPins(int number) {
        System.out.printf("%d프레임 투구 : ", number);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
