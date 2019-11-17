package view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.next();
    }

    public static int inputFrame(int frame) {
        System.out.println(frame + "프레임 투구 : ");
        return scanner.nextInt();
    }
}
