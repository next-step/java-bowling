package bowling.view;

import bowling.domain.PinCount;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String readName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static PinCount readPinCount(int roundCount) {
        System.out.print(String.format("%d 프레인 투구 : ", roundCount));
        int count = SCANNER.nextInt();
        SCANNER.nextLine();
        return PinCount.of(count);
    }
}
