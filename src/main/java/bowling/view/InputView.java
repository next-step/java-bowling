package bowling.view;

import bowling.domain.frame.Frame;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(ViewMessages.INSTRUCTION_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int inputPitch(Frame frame) {
        System.out.printf(ViewMessages.INSTRUCTION_PITCH, frame.getIndex());
        return Integer.parseInt(SCANNER.nextLine());
    }
}
