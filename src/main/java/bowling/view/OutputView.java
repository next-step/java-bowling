package bowling.view;

import bowling.dto.GameDto;
import bowling.view.printable.GamePrintable;

public class OutputView {
    private OutputView() {}

    public static void printGame(GameDto gameDto) {
        new GamePrintable(gameDto).print();
    }
}
