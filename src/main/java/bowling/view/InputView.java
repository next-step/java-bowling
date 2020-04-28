package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String BOWL_INPUT_MESSAGE = "%s's turn %d프레임 투구 : ";
    private static final String PLAYER_COUNT_INPUT_MESSAGE = "How many people? : ";

    public static String inputPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PLAYER_INPUT_MESSAGE);
        String input = scanner.nextLine().trim();

        return input;
    }

    public static String inputBowlCount(Player player, int frameNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(String.format(BOWL_INPUT_MESSAGE, player.getName(), frameNum));
        String input = scanner.nextLine().trim();

        return input;
    }

    public static int inputPlayerCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PLAYER_COUNT_INPUT_MESSAGE);
        int input = Integer.parseInt(scanner.nextLine().trim());

        return input;
    }
}
