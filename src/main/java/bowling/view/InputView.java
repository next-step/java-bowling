package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(ViewMessages.INSTRUCTION_PLAYER_NAME);
        return SCANNER.nextLine();
    }

/*    public static int inputPitch(Frames frames) {
        System.out.printf(ViewMessages.INSTRUCTION_PITCH, frames.getCurrentFrameIndex());
        return Integer.parseInt(SCANNER.nextLine());
    }*/
}
