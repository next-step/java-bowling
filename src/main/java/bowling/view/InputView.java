package bowling.view;

import bowling.domain.player.Name;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

public final class InputView {

    private static final String INPUT_PLAYER_COUNT_MESSAGE = "How many people? ";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %s의 이름은(3 english letters)?: ";
    private static final String INPUT_HIT_COUNT_MESSAGE = "%s's turn : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final class InputViewHolder {
        private static final InputView instance = new InputView();
    }

    public static final InputView getInstance() {
        return InputViewHolder.instance;
    }


    public final int InputPlayerCountByConsole() {
        System.out.println(String.format(INPUT_PLAYER_COUNT_MESSAGE));
        return valueOf(SCANNER.nextLine());
    }

    public final String InputPlayerNameByConsole() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public final int InputFallCountByConsole(final String name) {
        System.out.println(String.format(INPUT_HIT_COUNT_MESSAGE, name));
        return valueOf(SCANNER.nextLine());
    }

}
