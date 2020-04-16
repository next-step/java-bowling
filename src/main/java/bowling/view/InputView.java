package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String BOWL_INPUT_MESSAGE = "%d프레임 투구 : ";

    public static String inputPlayerNmae() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PLAYER_INPUT_MESSAGE);
        String input = scanner.nextLine().trim();

        return input;
    }

    public static String inputBowlCount(int frameNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(String.format(BOWL_INPUT_MESSAGE, frameNum));
        String input = scanner.nextLine().trim();

        return input;
    }
}
