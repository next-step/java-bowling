package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int hitNumberOfPin(int currentIndex) {
        System.out.printf("%d프레임 투구: ", currentIndex);
        return scanner.nextInt();
    }
}
