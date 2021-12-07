package bowling.view;

import bowling.domain.Game;
import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;

public class ConsoleOutputView implements OutputView {

    @Override
    public void resultsOfBowling(Game game) {
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
