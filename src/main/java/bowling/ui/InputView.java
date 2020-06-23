package bowling.ui;

import bowling.ui.exceptions.InvalidPlayerNameException;

import java.util.Scanner;

public class InputView {
    private static final Scanner USER_INPUT = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        String userName = USER_INPUT.nextLine();
        validateUserName(userName);
        return userName;
    }

    public static int getNumberOfHitPin(int frameIndex) {
        System.out.println(frameIndex + "프레임 투구 : ");
        int numberOfHitPin = USER_INPUT.nextInt();
        USER_INPUT.nextLine();  // 스캐너 초기화용

        return numberOfHitPin;
    }

    static void validateUserName(String userName) {
        String trimmed = userName.trim();
        if (trimmed.length() != 3) {
            throw new InvalidPlayerNameException("참가자 이름은 세글자여야 합니다.");
        }
    }
}
