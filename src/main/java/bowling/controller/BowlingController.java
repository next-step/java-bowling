package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.ui.Input;
import bowling.ui.Output;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BowlingController {

    private static final String MSG_INPUT_PLAYER_COUNT = "How many people? ";
    private static final String MSG_INPUT_NAME = "플레이어 %%의 이름은(3 english letters)?: ";
    private static final String MSG_INPUT_SWING = "%% 프레임 투구 : ";

    private static final String TAB_NAME = "NAME";
    private static final List<String> ROUND =
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private static final String BLANK = "";

    private static final String REPLACE_TARGET = "%%";

    private final Input input;
    private final Output output;
    private final int playerCount;
    private BowlingGame bowlingGame;

    public BowlingController(Input input, Output output) {
        this.input = input;
        this.output = output;

        output.print(MSG_INPUT_PLAYER_COUNT);
        playerCount = input.nextInt();
        input.nextLine();
    }

    public void inputNames() {
        bowlingGame = new BowlingGame(createNames(playerCount));
        printBoard();
    }

    private List<String> createNames(int playerCount) {
        return Stream.iterate(1, i -> i + 1)
                     .limit(playerCount)
                     .map(playerNo -> {
                         output.print(MSG_INPUT_NAME.replace(REPLACE_TARGET, String.valueOf(playerNo)));
                         return input.nextLine();
                     })
                     .collect(toList());
    }

    private void printBoard() {
        output.printRow(TAB_NAME, ROUND);
        output.printRow(bowlingGame.getName(), bowlingGame.getSwingRecords());
        output.printRow(BLANK, bowlingGame.getScores());
    }

    public void playGame() {
        while(bowlingGame.isBowlingGameRun()) {
            output.print(currentRoundInputMessage());
            bowlingGame.swing(input.nextInt());
            printBoard();
        }
    }

    private String currentRoundInputMessage() {
        return MSG_INPUT_SWING.replace(REPLACE_TARGET,
                String.valueOf(bowlingGame.currentRound()));
    }
}
