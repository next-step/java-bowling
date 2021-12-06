package bowling.view;

import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;

public class ConsoleOutputView implements OutputView {


    @Override
    public void questionPlayerName() {
        System.out.print("What is player's name? (3 English Letters)");
    }

    @Override
    public void questionPinNumber(int frame) {
        System.out.printf("Pin number of %d frame: ", frame);
    }

//    @Override
    public void resultsOfBowling() {
        stagesOfBowling();
        framesOfBowling();
    }

    private void stagesOfBowling() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|   NAME   ");
        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME)
                .forEach(i -> stringBuilder.append(String.format("|   %2d   ", i)));
        stringBuilder.append("|");

        System.out.println(stringBuilder);
    }

    private void framesOfBowling() {

    }
}
