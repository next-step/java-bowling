package bowling.view;

import bowling.dto.ResultDto;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String WHAT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String PITCH_MESSAGE = "%d프레임 투구 : ";

    private InputView() {}

    public static String playerName() {
        print(WHAT_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int pitch(final ResultDto resultDto) {
        print(String.format(PITCH_MESSAGE, resultDto.size()));
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static void print(final String message) {
        System.out.print(message);
    }
}
