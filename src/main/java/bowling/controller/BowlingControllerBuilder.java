package bowling.controller;

import bowling.ui.Input;
import bowling.ui.Output;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BowlingControllerBuilder {

    private static final String MSG_INPUT_PLAYER_COUNT = "How many people? ";
    private static final String MSG_INPUT_NAME = "플레이어 %%의 이름은(3 english letters)?: ";

    private Input input;
    private Output output;
    private int playerCount;
    private List<String> names;

    public BowlingControllerBuilder input(Input input) {
        this.input = input;
        return this;
    }

    public BowlingControllerBuilder output(Output output) {
        this.output = output;
        return this;
    }

    public BowlingControllerBuilder inputPlayerCount() {
        output.print(MSG_INPUT_PLAYER_COUNT);
        this.playerCount = input.nextInt();
        input.nextLine();
        return this;
    }

    public BowlingControllerBuilder inputNames() {
        this.names = createNames();
        return this;
    }

    private List<String> createNames() {
        return Stream.iterate(1, i -> i + 1)
                     .limit(playerCount)
                     .map(playerNo -> {
                         output.print(MSG_INPUT_NAME.replace(BowlingController.REPLACE_TARGET, String.valueOf(playerNo)));
                         return input.nextLine();
                     })
                     .collect(toList());
    }

    public BowlingController build() {
        return new BowlingController(input, output, names);
    }
}
