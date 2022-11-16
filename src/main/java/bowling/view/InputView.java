package bowling.view;

import bowling.domain.name.Name;
import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String INP_MSG_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)? ";
    public static final String INP_MSG_COUNT_OF_PIN = "%s's turn : ";
    public static final String INP_MSG_COUNT_OF_PLAYER = "How many people? ";
    public static Scanner scanner = new Scanner(System.in);

    public static List<Name> inputPlayersName(int countOfPlayer) {
        List<Name> names = new ArrayList<>();
        for (int i = 1; i <= countOfPlayer; i++) {
            System.out.printf(INP_MSG_PLAYER_NAME, i);
            names.add(new Name(scanner.nextLine()));
        }
        return names;
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
