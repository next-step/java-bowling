package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PITCH = "프레임 투구 :";

    private InputView() {
    }

    public static String getInputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int getInputPitch(int no) {
        System.out.printf(no + INPUT_PITCH);
        return scanner.nextInt();
    }
}
