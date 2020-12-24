package bowling_step3.view;

import java.util.Scanner;

public class InputUi {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUi() {

    }

    public static String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return SCANNER.next();
    }

    public static int inputFrame(int number) {
        System.out.print(String.format("%d프레임 투구 : ", number));
        return SCANNER.nextInt();
    }

    public static void close() {
        SCANNER.close();
    }
}
