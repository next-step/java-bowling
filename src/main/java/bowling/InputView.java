package bowling;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름(3 english letters)? : ");
        String name = scanner.nextLine();

        return name;
    }

    public static int inputFallenPins(int frameNumber) {
        System.out.print(String.format("%d프레임 투구 : ", frameNumber));

        return scanner.nextInt();
    }
}
