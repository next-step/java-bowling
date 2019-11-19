package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static String createUser() {
        System.out.println("플레이어 이름은(3 english letters)? :");
        return scanner.next();
    }

    public static int createScore(int frame) {
        System.out.println(String.format("%s프레임 투구 : ", frame));
        return scanner.nextInt();
    }
}
