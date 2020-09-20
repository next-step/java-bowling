package bowling.view;

import bowling.domain.frame.Frame;

import java.text.MessageFormat;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int getNumberOfPins(Frame frame) {
        System.out.println();
        System.out.print(MessageFormat.format("{0}프레임 투구 : ", frame.getFrameNumber()));
        return nextInt();
    }

    private static int nextInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

}
