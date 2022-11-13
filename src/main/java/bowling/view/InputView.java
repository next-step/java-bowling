package bowling.view;

import bowling.domain.name.Name;
import bowling.domain.Pin;

import java.util.Scanner;

public class InputView {

    public static final String INP_MSG_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)? ";
    public static final String INP_MSG_COUNT_OF_PIN = "%s's turn : ";
    public static final String INP_MSG_COUNT_OF_PLAYER = "How many people? ";
    public static Scanner scanner = new Scanner(System.in);

    public static Name inputPlayerName(int i) {
        System.out.printf(INP_MSG_PLAYER_NAME, i);
        return new Name(scanner.nextLine());
    }

    public static Pin inputCountOfPin(Name name) {
        System.out.printf(INP_MSG_COUNT_OF_PIN, name.getName());
        return Pin.of(scanner.nextLine());
    }

    public static int inputCountOfPlayer() {
        System.out.print(INP_MSG_COUNT_OF_PLAYER);
        return Integer.parseInt(scanner.nextLine());
    }

}
