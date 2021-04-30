package bowling.view;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    public static final String INPUT_FRAME_HITCOUNT_MESSAGE = "%s프레임 투구 : ";

    private static final class InputViewHolder {
        private static final InputView instance = new InputView();
    }

    private InputView() {
    }

    public static final InputView getInstance() {
        return InputViewHolder.instance;
    }

    public final String InputUserNameByClient() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public final int InputHitCountByClient(int round) {
        System.out.println(String.format(INPUT_FRAME_HITCOUNT_MESSAGE, round+1));
        return valueOf(SCANNER.nextLine());
    }

}
