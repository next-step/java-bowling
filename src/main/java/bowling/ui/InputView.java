package bowling.ui;

import bowling.domain.BowlingGame;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String FALLEN_PIN = "\n\n%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String inputPlayerName() {
        System.out.print(PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int nextFallenPin(BowlingGame bowlingGame) {
        System.out.printf(FALLEN_PIN, bowlingGame.currentFrameNumber());
        return Integer.parseInt(scanner.nextLine());
    }
}
