package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int inputScore(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
