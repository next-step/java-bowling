package bowling.view;

import java.util.Scanner;

public class InputView {
    public static String plzEnterUserName() {
        Printer.print("플레이어 이름은(3 english letters)?: ");
        final Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static Integer plzEnterFallenPins(final int nowFrameNo) {
        Printer.print(nowFrameNo + "프레임 투구 : ");
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
