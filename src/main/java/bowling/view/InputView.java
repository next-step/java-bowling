package bowling.view;

import bowling.domain.frame.FrameNumber;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int inputPinCount(FrameNumber frameNumber) {
        System.out.printf("%s 프레임 투구 : %n", frameNumber);

        int pinCount = inputNextInt();
        flushNewLine();
        return pinCount;
    }

    private static int inputNextInt() {
        return SCANNER.nextInt();
    }

    private static void flushNewLine() {
        SCANNER.nextLine();
    }
}
