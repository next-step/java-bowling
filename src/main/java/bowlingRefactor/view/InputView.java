package bowlingRefactor.view;

import bowlingRefactor.domain.FrameNumber;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER;

    static {
        SCANNER = new Scanner(System.in);
    }

    private InputView() {}

    public static String inputUserNames() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int inputPinCount(FrameNumber frameNumber) {
        System.out.printf("%s 프레임 투구 : ", frameNumber);
        return SCANNER.nextInt();
    }
}
