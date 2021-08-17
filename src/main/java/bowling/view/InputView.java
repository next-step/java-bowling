package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");

        return SCANNER.nextLine();
    }

    public static String inputPitch(final int frameCount) {
        System.out.print(frameCount + "프레임 투구 : ");

        return SCANNER.nextLine();
    }
}
