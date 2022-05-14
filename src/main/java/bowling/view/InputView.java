package bowling.view;

import bowling.domain.Pins;
import bowling.domain.Player;

import java.util.Scanner;

public final class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FRAME_PIN_COUNT = "%d 프레임 투구 : ";

    private final Scanner scanner = new Scanner(System.in);

    public Player inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return Player.create(readLine());
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public Pins inputPins(int currentRound) {
        System.out.printf(INPUT_FRAME_PIN_COUNT, currentRound);
        return Pins.create(readNumber());
    }

    private int readNumber() {
        return Integer.parseInt(readLine());
    }

}
