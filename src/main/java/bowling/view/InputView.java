package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_KNOCK_DOWN_PINS = "프레임 투구 : ";

    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName(){
        System.out.print(INPUT_PLAYER_NAME);
        String playerName = scanner.next();

        return playerName;
    }

    public static int inputKnockedDownPins(int frameIndex) {
        System.out.println();
        System.out.print(frameIndex + INPUT_KNOCK_DOWN_PINS);
        return scanner.nextInt();
    }
}
