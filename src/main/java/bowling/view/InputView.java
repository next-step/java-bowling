package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static String createUser() {
        System.out.println("플레이어 이름은(3 english letters)? :");
        String user = scanner.next();
        checkValidation(user.length());
        return user;
    }

    private static void checkValidation(int length) {
        if (length != 3) {
            throw new IllegalArgumentException("3 english letters 로 입력해주세요.");
        }
    }

    public static int createScore(int frame) {
        System.out.println(String.format("%s 프레임 투구 : ", frame));
        return scanner.nextInt();
    }
}
