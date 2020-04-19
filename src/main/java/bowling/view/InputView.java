package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private  static final int ONE = 1;
    private static final String PLAYER_COUNT_MESSAGE = "How many people?";
    private static final String NAME_MESSAGE =
            "플레이어 %d의 이름은?(3 english letters):";
    private static final String PITCHING_MESSAGE = "%s's turn :";

    public static List<String> getPlayerInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PLAYER_COUNT_MESSAGE);
        int playerCount = scanner.nextInt();
        return getNames(playerCount);
    }

    private static List<String> getNames(int count) {
        Scanner scanner = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            System.out.println(String.format(NAME_MESSAGE, i + ONE));
            names.add(scanner.nextLine());
        }

        return names;
    }

    public static int getPitching(String playerName) {
        System.out.println(String.format(PITCHING_MESSAGE, playerName));
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private InputView() {
    }
}
