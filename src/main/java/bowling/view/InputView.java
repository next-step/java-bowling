package bowling.view;

import java.util.Scanner;

public final class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_SCORE_MESSAGE_TEMPLATE = "%d 프레임 투구 : ";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public int getInputScore(final int frameNumber) {
        System.out.println();
        System.out.printf(INPUT_SCORE_MESSAGE_TEMPLATE, frameNumber);
        return Integer.parseInt(scanner.nextLine());
    }
}