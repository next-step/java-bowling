package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static final String INPUT_SHOT_MESSAGE = "%d프레임 투구 :";

    public static String inputName() {
        System.out.print(INPUT_NAME_MESSAGE);
        return "sid";//scanner.nextLine();
    }

    public static int inputShotResult(int currentFrame) {
        System.out.printf(INPUT_SHOT_MESSAGE, currentFrame);
        int number = 5;//scanner.nextInt();
        //flush();
        return number;
    }

    private static void flush() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
