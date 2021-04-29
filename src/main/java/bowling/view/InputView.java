package bowling.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final class InputViewHolder {
        private static final InputView instance = new InputView();
    }

    private InputView() {
    }

    public static final InputView getInstance() {
        return InputViewHolder.instance;
    }

    public final String InputUserNameByClient() {
        System.out.println();
        return SCANNER.nextLine().trim();
    }

}
