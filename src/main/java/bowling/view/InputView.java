package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?:\n";
    private static final String CLEAR_PIN = "%d프레임 투구 :\n";

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputPlayerName() {
        System.out.printf(PLAYER_NAME);

        return scanner.nextLine();
    }

    public int inputClearPin(int frameNumber) {
        if (frameNumber == 0) {
            frameNumber++;
        }

        System.out.printf(CLEAR_PIN, frameNumber);

        return scanner.nextInt();
    }
}
