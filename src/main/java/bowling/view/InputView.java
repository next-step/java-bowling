package bowling.view;

import bowling.domain.BowlingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FRAME_PITCHING = "프레임 투구 : ";
    private static final String INPUT_PLAYER_COUNT = "How many people? ";

    private InputView() {
    }

    public static int inputPlayerCount() {
        System.out.print(INPUT_PLAYER_COUNT);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<String> inputPlayerName(int count) {
        List<String> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.print(INPUT_PLAYER_NAME);
            players.add(SCANNER.nextLine());
        }
        return players;
    }

    public static int inputPlayerTurn(final BowlingGame game) {
        System.out.println();
        System.out.print(game.player() + "'s turn : ");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
