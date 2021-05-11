package bowling.view;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

public final class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_HIT_COUNT_MESSAGE = "%s프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final class InputViewHolder {
        private static final InputView instance = new InputView();
    }

    public static final InputView getInstance() {
        return InputViewHolder.instance;
    }

    public final String InputPlayerNameByConsole() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public final int InputHitCountByConsole(final int index) {
        System.out.println(String.format(INPUT_HIT_COUNT_MESSAGE, index));
        return valueOf(SCANNER.nextLine());
    }

}
