package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int inputBowl(int frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber);
        return Integer.parseInt(scanner.nextLine());
    }

}
