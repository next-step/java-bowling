package bowling.view;

import bowling.domain.Game;

import java.util.stream.IntStream;

import static bowling.controller.BowlingGame.*;

public class ConsoleOutputView implements OutputView {
    private static final char BAR = '|';

    private static final String EMPTY_FRAME = "|        ";
    private static final String STAGES = "|   %2d   ";
    private static final String NAME_TITLE = "|   NAME   ";
    private static final String NAME_FIELD = "|    %s   ";
    private static final String PIN_FIELD = "|  %-5s ";

    @Override
    public void resultsOfBowling(Game game) {
        stagesOfBowling();
        framesOfBowling(game);
    }

    private void stagesOfBowling() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NAME_TITLE);
        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME)
                .forEach(i -> stringBuilder.append(String.format(STAGES, i)));
        stringBuilder.append(BAR);

        System.out.println(stringBuilder);
    }

    private void framesOfBowling(Game game) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(NAME_FIELD, game.getName()));

        IntStream.rangeClosed(FIRST_FRAME, game.frameNumber())
                        .forEach(frameNumber ->
                                stringBuilder.append(String.format(PIN_FIELD, game.frameState(frameNumber))));

        IntStream.rangeClosed(FIRST_FRAME, LAST_FRAME - game.frameNumber())
                        .forEach(i -> stringBuilder.append(fillEmptyFrame()));
        stringBuilder.append(BAR);

        System.out.println(stringBuilder);
    }

    private StringBuilder fillEmptyFrame() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EMPTY_FRAME);

        return stringBuilder;
    }
}
