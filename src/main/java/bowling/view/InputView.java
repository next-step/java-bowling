package bowling.view;

import bowling.domain.Name;
import bowling.domain.Pin;

import java.util.Scanner;

public class InputView {

    public static final String INP_MSG_PLAYER_NAME = "플레이어 이름은(3 english letters)? ";
    public static final String INPUT_MSG_COUNT_OF_PIN = "%d프레임 투구 : ";
    public static Scanner scanner = new Scanner(System.in);

    public static Name inputPlayerName() {
        System.out.print(INP_MSG_PLAYER_NAME);
        return new Name(scanner.nextLine());
    }

    public static Pin inputCountOfPin(int frameNo) {
        System.out.printf(INPUT_MSG_COUNT_OF_PIN, frameNo);
        return Pin.of(scanner.nextLine());
    }

}
