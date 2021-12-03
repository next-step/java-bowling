package bowling.view;

import java.util.stream.IntStream;

public class ConsoleOutputView implements OutputView {
    private static int FIRST_FRAME = 1;
    private static int LAST_FRAME = 10;

    @Override
    public void questionPlayerName() {
        System.out.print("What is player's name? (3 English Letters)");
    }

    @Override
    public void questionPinNumber(int frame) {
        System.out.printf("Pin number of %d frame: ", frame);
    }

    @Override
    public void stagesOfBowling() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|   NAME   ");
        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME)
                .forEach(i -> stringBuilder.append(String.format("|   %2d   ", i)));
        stringBuilder.append("|");

        System.out.println(stringBuilder);
    }

    @Override
    public void framesOfBowling() {

    }
}
