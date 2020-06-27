package bowling.view;

import bowling.domain.frame.Frames;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(ViewMessage.INSTRUCTION_PLAYER_NAME);
        return SCANNER.nextLine().toUpperCase().trim();
    }

    public static int inputPitchScore(Frames frames) {
        System.out.printf(ViewMessage.INSTRUCTION_PITCH, frames.getCurrentIndex());
        return Integer.parseInt(SCANNER.nextLine());
    }
}
