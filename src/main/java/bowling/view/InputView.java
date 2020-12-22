package bowling.view;

import bowling.domain.Player;
import bowling.domain.point.Point;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
    public static final String FRAME_PITCH = "'s turn : ";
    public static final String HOW_MANY_PEOPLE = "How many people?";

    public static Player inputPlayerName() {
        System.out.println(PLAYER_NAME);
        return Player.of(scanner.nextLine());
    }

    public static Point inputPitchBowl(String playerName) {
        System.out.print(playerName + FRAME_PITCH);
        return Point.valueOfString(scanner.nextLine());
    }


    public static int inputParticipatePeople() {
        System.out.println(HOW_MANY_PEOPLE);
        return Integer.parseInt(scanner.nextLine());
    }

}
